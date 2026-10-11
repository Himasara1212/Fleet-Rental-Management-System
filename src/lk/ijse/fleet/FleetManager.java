package lk.ijse.fleet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FleetManager {
    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<RentalAgreement> agreements;
    private RentalEventListener rentalEventListener;

    public FleetManager() {
        this.vehicles = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.agreements = new ArrayList<>();
        this.rentalEventListener = null;
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void setRentalEventListener(RentalEventListener listener) {
        this.rentalEventListener = listener;
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicles.stream()
                .filter(v -> v.getStatus() == RentalStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    public RentalAgreement createRentalAgreement(String vehicleId, String customerId,
                                                 int rentalDays)
            throws VehicleNotAvailableException, InsufficientCreditException {

        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found: " + vehicleId);
            return null;
        }

        if (vehicle.getStatus() != RentalStatus.AVAILABLE) {
            throw new VehicleNotAvailableException(
                    "Vehicle " + vehicleId + " is not available for rent.");
        }

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found: " + customerId);
            return null;
        }

        double subtotal = vehicle.calculateRentalCost(rentalDays);
        double cost = customer.applyDiscount(subtotal);

        if (customer instanceof CorporateCustomer) {
            CorporateCustomer corp = (CorporateCustomer) customer;
            if (!corp.hasSufficientCredit(cost)) {
                throw new InsufficientCreditException(
                        "Customer " + customerId + " has insufficient credit for this rental.");
            }
        }

        RentalAgreement agreement = new RentalAgreement(vehicle, customer, rentalDays);
        vehicle.setStatus(RentalStatus.RENTED);
        agreements.add(agreement);

        if (rentalEventListener != null) {
            rentalEventListener.onRentalCreated(agreement);
        }

        return agreement;
    }

    public void returnVehicle(String agreementId) {
        for (RentalAgreement a : agreements) {
            if (a.getAgreementId().equals(agreementId)) {
                a.getVehicle().setStatus(RentalStatus.AVAILABLE);
                System.out.println("Vehicle " + a.getVehicle().getVehicleId() +
                        " returned successfully.");
                return;
            }
        }
        System.out.println("Agreement not found: " + agreementId);
    }

    public FleetSnapshot generateSnapshot() {
        return new FleetSnapshot();
    }

    public synchronized void printFleetStatus() {
        generateSnapshot().printSummary();
    }

    public synchronized void checkOverdueAgreements() {
        for (RentalAgreement a : agreements) {
            a.setDaysElapsed(a.getDaysElapsed() + 1);
            if (a.getDaysElapsed() == a.getRentalDays() + 1) {
                System.out.println("[OVERDUE ALERT] Agreement " + a.getAgreementId() +
                        " has exceeded its " + a.getRentalDays() + "-day rental period.");
            }
        }
    }

    public List<Vehicle> getVehiclesByFuelType(FuelType type) {
        return vehicles.stream()
                .filter(v -> v.getFuelType() == type)
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomersSortedByName() {
        return customers.stream()
                .sorted(Comparator.comparing(c -> c.getFullName()))
                .collect(Collectors.toList());
    }

    public double generateRevenueReport() {
        return agreements.stream()
                .mapToDouble(a -> a.getTotalCost())
                .sum();
    }

    public void printAllReportables() {
        List<Reportable> reportables = new ArrayList<>();
        reportables.addAll(vehicles);
        reportables.addAll(customers);

        for (Reportable item : reportables) {
            System.out.println(item.getReportCategory() + ": " + item.getSummary());
        }
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public List<RentalAgreement> getAllAgreements() {
        return agreements;
    }

    public class FleetSnapshot {
        private int availableCount;
        private int rentedCount;
        private double totalRevenue;

        public FleetSnapshot() {
            this.availableCount = 0;
            this.rentedCount = 0;
            this.totalRevenue = 0;

            for (Vehicle v : vehicles) {
                if (v.getStatus() == RentalStatus.AVAILABLE) {
                    availableCount++;
                } else if (v.getStatus() == RentalStatus.RENTED) {
                    rentedCount++;
                }
            }

            for (RentalAgreement a : agreements) {
                totalRevenue += a.getTotalCost();
            }
        }

        public void printSummary() {
        }
    }
}