package lk.ijse.fleet;

@FunctionalInterface
public interface RentalEventListener {
    void onRentalCreated(RentalAgreement agreement);
}