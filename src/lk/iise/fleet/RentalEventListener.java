package lk.iise.fleet;

@FunctionalInterface
public interface RentalEventListener {
    void onRentalCreated(RentalAgreement agreement);
}