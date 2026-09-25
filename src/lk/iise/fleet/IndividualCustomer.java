package lk.iise.fleet;

public class IndividualCustomer extends Customer {
    private int loyaltyPoints;

    public IndividualCustomer(String fullName, String contactNumber, int loyaltyPoints) {
        super(fullName, contactNumber);
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public double applyDiscount(double subtotal) {
        return subtotal;
    }
}