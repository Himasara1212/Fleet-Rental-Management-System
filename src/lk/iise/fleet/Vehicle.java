package lk.iise.fleet;

public abstract class Vehicle implements Reportable {
    private static int counter = 1000;

    private String vehicleId;
    private String registrationNumber;
    private String brand;
    private String model;
    private double dailyRate;
    private FuelType fuelType;
    private RentalStatus status;

    public Vehicle(String registrationNumber, String brand, String model,
                   double dailyRate, FuelType fuelType) {
        this.vehicleId = "VEH-" + (++counter);
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.fuelType = fuelType;
        this.status = RentalStatus.AVAILABLE;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public abstract double calculateRentalCost(int rentalDays);

    @Override
    public String getSummary() {
        return vehicleId + " - " + brand + " " + model + " [" + status + "]";
    }

    @Override
    public String getReportCategory() {
        return "VEHICLE";
    }

    @Override
    public String toString() {
        return "Vehicle ID: " + vehicleId +
                ", Reg: " + registrationNumber +
                ", Brand: " + brand +
                ", Model: " + model +
                ", Daily Rate: " + dailyRate +
                ", Fuel: " + fuelType +
                ", Status: " + status;
    }
}