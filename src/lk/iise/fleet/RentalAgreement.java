package lk.iise.fleet;

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
        this.totalCost = 0;
        this.daysElapsed = 0;
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
        return "Agreement ID: " + agreementId +
                ", Vehicle: " + vehicle.getVehicleId() +
                ", Customer: " + customer.getCustomerId() +
                ", Days: " + rentalDays +
                ", Total Cost: " + totalCost;
    }
}