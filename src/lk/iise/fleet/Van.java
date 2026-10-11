package lk.iise.fleet;

public class Van extends Vehicle {
    private int passengerCapacity;

    public Van(String registrationNumber, String brand, String model,
               double dailyRate, FuelType fuelType, int passengerCapacity) {
        super(registrationNumber, brand, model, dailyRate, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        double baseCost = getDailyRate() * rentalDays * getFuelType().getCostMultiplier();
        double vanSurcharge = getDailyRate() * 0.20;
        return baseCost + vanSurcharge;
    }
}