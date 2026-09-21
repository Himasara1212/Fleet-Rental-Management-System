package lk.iise.fleet;

public class VehicleNotAvailableException extends Exception {
    public VehicleNotAvailableException(String message) {
        super(message);
    }
}