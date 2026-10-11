package lk.ijse.fleet;

public enum FuelType {
    PETROL(1.00),
    DIESEL(0.90),
    ELECTRIC(0.75),
    HYBRID(0.85);

    private final double costMultiplier;

    FuelType(double costMultiplier) {
        this.costMultiplier = costMultiplier;
    }

    public double getCostMultiplier() {
        return costMultiplier;
    }
}