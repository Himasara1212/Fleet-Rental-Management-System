package lk.ijse.fleet;

public class RentalAgreement {
    private static int counter = 1000;

    private String agreementId;
    private Vehicle vehicle;
    private Customer customer;
    private int rentalDays;
    private double totalCost;
    private int daysElapsed;

    public RentalAgreement(Vehicle vehicle, Customer customer, int rentalDays) {
        this.agreementId = "AGR-" + (++counter);
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;
        this.daysElapsed = 0;

        // Calculate the total cost using polymorphic cost and discount formulas
        double subtotal = vehicle.calculateRentalCost(rentalDays);
        this.totalCost = customer.applyDiscount(subtotal);
    }

    public String getAgreementId() {
        return agreementId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getDaysElapsed() {
        return daysElapsed;
    }

    public void setDaysElapsed(int daysElapsed) {
        this.daysElapsed = daysElapsed;
    }

    @Override
    public String toString() {
        return "\n================================================================\n" +
                "RENTAL AGREEMENT RECEIPT\n" +
                "================================================================\n" +
                "Agreement ID : " + agreementId + "\n" +
                "Vehicle      : " + vehicle.getVehicleId() + " - " +
                vehicle.getBrand() + " " + vehicle.getModel() +
                " (" + vehicle.getClass().getSimpleName() + ")\n" +
                "Customer     : " + customer.getCustomerId() + " - " +
                customer.getFullName() +
                " (" + customer.getClass().getSimpleName() + ")\n" +
                "Rental Days  : " + rentalDays + "\n" +
                "Total Cost   : " + String.format("%,.2f", totalCost) + "\n" +
                "================================================================";
    }
}