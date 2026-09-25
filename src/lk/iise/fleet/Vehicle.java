package lk.iise.fleet;

public abstract class Vehicle implements Reportable {
    private static int n = 1001;

    private String id;
    private String reg;
    private String brand;
    private String model;
    private double rate;
    private FuelType fuel;
    private RentalStatus status;

    public Vehicle(String reg, String brand, String model, double rate, FuelType fuel) {
        this.id = "VEH-" + n;
        n = n + 1;
        this.reg = reg;
        this.brand = brand;
        this.model = model;
        this.rate = rate;
        this.fuel = fuel;
        this.status = RentalStatus.AVAILABLE;
    }

    public abstract double calculateRentalCost(int days);

    public String getVehicleId() {
        return id; }

    public String getRegistrationNumber() {
        return reg;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRate() {
        return rate;
    }

    public FuelType getFuelType() {
        return fuel;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus s) {
        this.status = s;
    }

    public String getSummary() {
        return id + " - " + brand + " " + model;
    }

    public String getReportCategory() {
        return "VEHICLE";
    }

    public String toString() {
        return id + " | " + reg + " | " + brand + " " + model + " | " + fuel + " | " + status;
    }
}