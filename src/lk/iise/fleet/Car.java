package lk.iise.fleet;

public class Car extends Vehicle {

    public Car(String reg, String brand, String model, double rate, FuelType fuel) {
        super(reg, brand, model, rate, fuel);
    }

    public double calculateRentalCost(int days) {
        return getDailyRate() * days * getFuelType().getCostMultiplier();
    }
}