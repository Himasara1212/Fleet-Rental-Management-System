package lk.iise.fleet;

public class Van extends Vehicle {
    private int seats;

    public Van(String reg, String brand, String model, double rate, FuelType fuel, int seats) {
        super(reg, brand, model, rate, fuel);
        this.seats = seats;
    }

    public int getPassengerCapacity() {
        return seats;
    }

    public double calculateRentalCost(int days) {
        double base = getDailyRate() * days * getFuelType().getCostMultiplier();
        return base + (getDailyRate() * 0.20);
    }
}