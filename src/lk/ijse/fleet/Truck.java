package lk.ijse.fleet;

public class Truck extends Vehicle {
    private double cargoCapacityTons;

    public Truck(String registrationNumber, String brand, String model,
                 double dailyRate, FuelType fuelType, double cargoCapacityTons) {
        super(registrationNumber, brand, model, dailyRate, fuelType);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    public void setCargoCapacityTons(double cargoCapacityTons) {
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        double baseCost = getDailyRate() * rentalDays * getFuelType().getCostMultiplier();
        double cargoSurcharge = cargoCapacityTons * 10.00 * rentalDays;
        return baseCost + cargoSurcharge;
    }
}