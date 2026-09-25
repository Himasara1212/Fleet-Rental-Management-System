package lk.iise.fleet;

public class InsufficientCreditException extends Exception {
    public InsufficientCreditException(String message) {
        super(message);
    }
}