package lk.iise.fleet;

public class Truck extends Vehicle {
    private double tons;

    public Truck(String reg, String brand, String model, double rate, FuelType fuel, double tons) {
        super(reg, brand, model, rate, fuel);
        this.tons = tons;
    }

    public double getCargoCapacityTons() {
        return tons;
    }

    public double calculateRentalCost(int days) {
        double base = getDailyRate() * days * getFuelType().getCostMultiplier();
        return base + (tons * 10.00 * days);
    }
}