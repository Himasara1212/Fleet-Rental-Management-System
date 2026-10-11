package lk.iise.fleet;

import java.util.List;
import java.util.Scanner;

public class Main {
    private FleetManager fleetManager;
    private UserManager userManager;
    private Scanner scanner;
    private User loggedInUser;

    public Main() {
        this.fleetManager = new FleetManager();
        this.userManager = new UserManager();
        this.scanner = new Scanner(System.in);
        this.loggedInUser = null;

        fleetManager.setRentalEventListener(agreement ->
                System.out.println("[EVENT] New rental created: " + agreement.getAgreementId())
        );

        Thread statusThread = new Thread(new VehicleStatusMonitor(fleetManager));
        statusThread.setDaemon(true);
        statusThread.start();

        Thread overdueThread = new Thread(new OverdueReminderWorker(fleetManager));
        overdueThread.setDaemon(true);
        overdueThread.start();
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        showAuthMenu();
    }

    public void showAuthMenu() {
        while (true) {
            System.out.println("\n========================================================");
            System.out.println("FLEET RENTAL MANAGEMENT SYSTEM (FRMS)");
            System.out.println("========================================================");
            System.out.println("[1] Login");
            System.out.println("[2] Register New User");
            System.out.println("[0] Exit");
            System.out.print("Enter choice > ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String u = scanner.nextLine();
                    System.out.print("Password: ");
                    String p = scanner.nextLine();
                    User user = userManager.login(u, p);
                    if (user != null) {
                        loggedInUser = user;
                        System.out.println("Login successful!");
                        showMainMenu();
                    } else {
                        System.out.println("Invalid username or password");
                    }
                    break;

                case 2:
                    System.out.print("Username: ");
                    String ru = scanner.nextLine();
                    System.out.print("Password: ");
                    String rp = scanner.nextLine();
                    System.out.print("Full Name: ");
                    String rn = scanner.nextLine();
                    userManager.registerUser(ru, rp, rn);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void showMainMenu() {
        while (loggedInUser != null) {
            System.out.println("\n========================================================");
            System.out.println("Welcome, " + loggedInUser.getFullName() + "...");
            System.out.println("========================================================");
            System.out.println("FRMS MAIN MENU | Logged in as: " + loggedInUser.getFullName());
            System.out.println("========================================================");
            System.out.println("[1] Add Vehicle");
            System.out.println("[2] Add Customer");
            System.out.println("[3] View Available Vehicles");
            System.out.println("[4] Create Rental Agreement");
            System.out.println("[5] Return Vehicle");
            System.out.println("[6] View Revenue Report");
            System.out.println("[7] View Vehicles by Fuel Type");
            System.out.println("[8] View Combined Fleet & Customer Report");
            System.out.println("[9] View All Customers");
            System.out.println("[10] View All Rental Agreements");
            System.out.println("[0] Logout");
            System.out.print("Enter choice > ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    viewAvailableVehicles();
                    break;
                case 4:
                    createRental();
                    break;
                case 5:
                    returnVehicle();
                    break;
                case 6:
                    viewRevenueReport();
                    break;
                case 7:
                    viewVehiclesByFuelType();
                    break;
                case 8:
                    fleetManager.printAllReportables();
                    break;
                case 9:
                    viewAllCustomers();
                    break;
                case 10:
                    viewAllAgreements();
                    break;
                case 0:
                    loggedInUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addVehicle() {
        System.out.println("\n--- Add Vehicle ---");
        System.out.println("[1] Car  [2] Van  [3] Truck");
        System.out.print("Type > ");
        int type;
        try {
            type = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid type!");
            return;
        }

        System.out.print("Registration Number: ");
        String reg = scanner.nextLine();
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Daily Rate: ");
        double rate;
        try {
            rate = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid rate!");
            return;
        }

        FuelType fuel = selectFuelType();
        if (fuel == null) return;

        Vehicle v;
        switch (type) {
            case 1:
                v = new Car(reg, brand, model, rate, fuel);
                break;
            case 2:
                System.out.print("Passenger Capacity: ");
                int cap;
                try {
                    cap = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid capacity!");
                    return;
                }
                v = new Van(reg, brand, model, rate, fuel, cap);
                break;
            case 3:
                System.out.print("Cargo Capacity (tons): ");
                double tons;
                try {
                    tons = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid cargo capacity!");
                    return;
                }
                v = new Truck(reg, brand, model, rate, fuel, tons);
                break;
            default:
                System.out.println("Invalid vehicle type!");
                return;
        }

        fleetManager.addVehicle(v);
        System.out.println("Vehicle added: " + v.getVehicleId());
    }

    private FuelType selectFuelType() {
        System.out.println("Fuel Type: [1] PETROL [2] DIESEL [3] ELECTRIC [4] HYBRID");
        System.out.print("Choice > ");
        try {
            int f = Integer.parseInt(scanner.nextLine());
            switch (f) {
                case 1: return FuelType.PETROL;
                case 2: return FuelType.DIESEL;
                case 3: return FuelType.ELECTRIC;
                case 4: return FuelType.HYBRID;
                default:
                    System.out.println("Invalid fuel type!");
                    return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid fuel type!");
            return null;
        }
    }

    private void addCustomer() {
        System.out.println("\n--- Add Customer ---");
        System.out.println("[1] Individual  [2] Corporate");
        System.out.print("Type > ");
        int type;
        try {
            type = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid type!");
            return;
        }

        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Contact Number: ");
        String contact = scanner.nextLine();

        Customer c;
        if (type == 1) {
            System.out.print("Loyalty Points: ");
            int points;
            try {
                points = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid loyalty points!");
                return;
            }
            c = new IndividualCustomer(name, contact, points);
        } else if (type == 2) {
            System.out.print("Credit Limit: ");
            double limit;
            try {
                limit = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid credit limit!");
                return;
            }
            c = new CorporateCustomer(name, contact, limit);
        } else {
            System.out.println("Invalid customer type!");
            return;
        }

        fleetManager.addCustomer(c);
        System.out.println("Customer added: " + c.getCustomerId());
    }

    private void viewAvailableVehicles() {
        List<Vehicle> available = fleetManager.getAvailableVehicles();
        if (available.isEmpty()) {
            System.out.println("No available vehicles.");
            return;
        }
        System.out.println("\n--- Available Vehicles ---");
        for (int i = 0; i < available.size(); i++) {
            System.out.println(available.get(i));
        }
    }

    private void createRental() {
        System.out.print("Vehicle ID: ");
        String vid = scanner.nextLine();
        System.out.print("Customer ID: ");
        String cid = scanner.nextLine();
        System.out.print("Rental Days: ");
        int days;
        try {
            days = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of days!");
            return;
        }

        try {
            RentalAgreement agreement =
                    fleetManager.createRentalAgreement(vid, cid, days);
            if (agreement != null) {
                System.out.println(agreement);
            }
        } catch (VehicleNotAvailableException | InsufficientCreditException e) {
            System.out.println("Rental failed: " + e.getMessage());
        }
    }

    private void returnVehicle() {
        System.out.print("Agreement ID: ");
        String aid = scanner.nextLine();
        fleetManager.returnVehicle(aid);
    }

    private void viewRevenueReport() {
        double revenue = fleetManager.generateRevenueReport();
        System.out.println("\n========================================================");
        System.out.println("FLEET REVENUE REPORT");
        System.out.println("========================================================");
        System.out.println("Total Revenue: " + revenue);
        System.out.println("--------------------------------------------------------");
    }

    private void viewVehiclesByFuelType() {
        FuelType fuel = selectFuelType();
        if (fuel == null) return;
        List<Vehicle> list = fleetManager.getVehiclesByFuelType(fuel);
        if (list.isEmpty()) {
            System.out.println("No vehicles with fuel type " + fuel);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private void viewAllCustomers() {
        List<Customer> list = fleetManager.getAllCustomers();
        if (list.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private void viewAllAgreements() {
        List<RentalAgreement> list = fleetManager.getAllAgreements();
        if (list.isEmpty()) {
            System.out.println("No rental agreements.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}