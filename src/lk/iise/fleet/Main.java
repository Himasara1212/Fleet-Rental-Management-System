package lk.iise.fleet;

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
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        showAuthMenu();
    }

    public void showAuthMenu() {
        while (true) {
            System.out.println("\n---------------------------------------");
            System.out.println("      Fleet Rental Management System       ");
            System.out.println("-------------------------------------------");
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[0] Exit");
            System.out.print("Choice > ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
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
                        System.out.println("Wrong username or password");
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
        System.out.println("\n--- Main Menu (Coming in Mission 2) ---");
    }
}