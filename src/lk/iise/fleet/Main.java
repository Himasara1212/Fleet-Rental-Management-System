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
        System.out.println("\n===== Fleet Rental Management System =====");
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
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("[1] Add Vehicle");
            System.out.println("[2] Add Customer");
            System.out.println("[0] Logout");
            System.out.print("Choice > ");

            String input = sc.nextLine();

            if (input.equals("1")) {
                System.out.println("Add Vehicle - coming soon");
            } else if (input.equals("2")) {
                System.out.println("Add Customer - coming soon");
            } else if (input.equals("0")) {
                currentUser = null;
                System.out.println("Logged out");
            } else {
                System.out.println("Wrong choice");
            }
        }
    }
}