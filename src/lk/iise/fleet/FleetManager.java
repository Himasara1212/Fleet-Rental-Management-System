package lk.iise.fleet;

import java.util.ArrayList;
import java.util.List;

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
    }

    public void addCustomer(Customer c) {
    }

    public Vehicle findVehicleById(String id) {
        return null;
    }

    public Customer findCustomerById(String id) {
        return null;
    }

    public void setRentalEventListener(RentalEventListener listener) {
        this.rentalEventListener = listener;
    }

    public List<Vehicle> getAvailableVehicles() {
        return new ArrayList<>();
    }

    public RentalAgreement createRentalAgreement(String vehicleId, String customerId,
                                                 int rentalDays)
            throws VehicleNotAvailableException, InsufficientCreditException {
        return null;
    }

    public void returnVehicle(String agreementId) {
    }

    public FleetSnapshot generateSnapshot() {
        return new FleetSnapshot();
    }

    public synchronized void printFleetStatus() {
    }

    public synchronized void checkOverdueAgreements() {
    }

    public List<Vehicle> getVehiclesByFuelType(FuelType type) {
        return new ArrayList<>();
    }

    public List<Customer> getCustomersSortedByName() {
        return new ArrayList<>();
    }

    public double generateRevenueReport() {
        return 0;
    }

    public void printAllReportables() {
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>();
    }

    public List<RentalAgreement> getAllAgreements() {
        return new ArrayList<>();
    }

    public class FleetSnapshot {
        private int availableCount;
        private int rentedCount;
        private double totalRevenue;

        public FleetSnapshot() {
            this.availableCount = 0;
            this.rentedCount = 0;
            this.totalRevenue = 0;
        }

        public void printSummary() {
        }
    }
}