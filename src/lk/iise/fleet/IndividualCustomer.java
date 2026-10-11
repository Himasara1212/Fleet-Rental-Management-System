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
        double discountRate;

        if (loyaltyPoints < 100) {
            discountRate = 0.00;
        } else if (loyaltyPoints < 300) {
            discountRate = 0.05;
        } else {
            discountRate = 0.10;
        }

        return subtotal * (1 - discountRate);
    }
}