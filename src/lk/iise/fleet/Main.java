package lk.iise.fleet;

import java.util.Scanner;

public class Main {

    private FleetManager fm;
    private UserManager um;
    private Scanner sc;
    private User currentUser;

    public Main() {
        fm = new FleetManager();
        um = new UserManager();
        sc = new Scanner(System.in);
        currentUser = null;
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        while (currentUser == null) {
            showAuthMenu();
        }
        showMainMenu();
    }

    public void showAuthMenu() {
        System.out.println("\n---------------------------------------");
        System.out.println("      Fleet Rental Management System       ");
        System.out.println("-------------------------------------------");
        System.out.println("[1] Login");
        System.out.println("[2] Register");
        System.out.println("[0] Exit");

        System.out.print("Choice > ");
        String input = sc.nextLine();

        if (input.equals("1")) {
            System.out.print("Username: ");
            String u = sc.nextLine();
            System.out.print("Password: ");
            String p = sc.nextLine();

            User user = um.login(u, p);

            if (user != null) {
                currentUser = user;
                System.out.println("Login OK!");
            } else {
                System.out.println("Wrong username or password");
            }

        } else if (input.equals("2")) {
            System.out.print("Username: ");
            String u = sc.nextLine();
            System.out.print("Password: ");
            String p = sc.nextLine();
            System.out.print("Full Name: ");
            String f = sc.nextLine();

            um.registerUser(u, p, f);

        } else if (input.equals("0")) {
            System.out.println("Bye!");
            System.exit(0);

        } else {
            System.out.println("Wrong choice");
        }
    }

    public void showMainMenu() {
        System.out.println("\nHello " + currentUser.getFullName() + "!");

        while (currentUser != null) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("[1] Add Vehicle");
            System.out.println("[2] Add Customer");
            System.out.println("[3] View Vehicles");
            System.out.println("[0] Logout");
            System.out.print("Choice > ");

            String input = sc.nextLine();

            if (input.equals("1")) {
                addVehicle();
            } else if (input.equals("2")) {
                System.out.println("Add Customer");
            } else if (input.equals("3")) {
                showVehicles();
            } else if (input.equals("0")) {
                currentUser = null;
                System.out.println("Logged out");
            } else {
                System.out.println("Wrong choice");
            }
        }
    }

    // ===== Add Vehicle =====
    public void addVehicle() {
        System.out.print("Type (1=Car 2=Van 3=Truck): ");
        String type = sc.nextLine();

        System.out.print("Reg No: ");
        String reg = sc.nextLine();

        System.out.print("Brand: ");
        String brand = sc.nextLine();

        System.out.print("Model: ");
        String model = sc.nextLine();

        System.out.print("Daily Rate: ");
        String rateStr = sc.nextLine();

        double rate = 0;
        try {
            rate = Double.parseDouble(rateStr);
        } catch (Exception e) {
            System.out.println("Wrong rate");
            return;
        }

        System.out.print("Fuel (1=PETROL 2=DIESEL 3=ELECTRIC 4=HYBRID): ");
        String fuelStr = sc.nextLine();

        FuelType fuel = FuelType.PETROL;
            if (fuelStr.equals("2")) {
                fuel = FuelType.DIESEL;
            } else if (fuelStr.equals("3")) {
                fuel = FuelType.ELECTRIC;
            } else if (fuelStr.equals("4")) {
                fuel = FuelType.HYBRID;
            }

        if (type.equals("1")) {
            Car c = new Car(reg, brand, model, rate, fuel);
            fm.vehicleList.add(c);
            System.out.println("Car added: " + c.getVehicleId());

        } else if (type.equals("2")) {
            System.out.print("Passenger Capacity: ");
            String pcStr = sc.nextLine();

            int pc = 0;
            try {
                pc = Integer.parseInt(pcStr);
            } catch (Exception e) {
                System.out.println("Wrong capacity");
                return;
            }

            Van v = new Van(reg, brand, model, rate, fuel, pc);
            fm.vehicleList.add(v);
            System.out.println("Van added: " + v.getVehicleId());

        } else if (type.equals("3")) {
            System.out.print("Cargo Tons: ");
            String ccStr = sc.nextLine();

            double cc = 0;
            try {
                cc = Double.parseDouble(ccStr);
            } catch (Exception e) {
                System.out.println("Wrong tons");
                return;
            }

            Truck t = new Truck(reg, brand, model, rate, fuel, cc);
            fm.vehicleList.add(t);
            System.out.println("Truck added: " + t.getVehicleId());

        } else {
            System.out.println("Wrong type");
        }
    }


    public void showVehicles() {
        if (fm.vehicleList.size() == 0) {
            System.out.println("No vehicles");
        } else {
            for (int i = 0; i < fm.vehicleList.size(); i++) {
                System.out.println(fm.vehicleList.get(i));
            }
        }
    }
}