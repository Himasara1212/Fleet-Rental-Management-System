package lk.iise.fleet;

public class Car extends Vehicle {

    public Car(String registrationNumber, String brand, String model,
               double dailyRate, FuelType fuelType) {
        super(registrationNumber, brand, model, dailyRate, fuelType);
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return 0;
    }
}