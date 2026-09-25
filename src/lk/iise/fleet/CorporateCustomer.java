package lk.iise.fleet;

public class CorporateCustomer extends Customer {
    private double limit;
    private double balance;

    public CorporateCustomer(String name, String contact, double limit) {
        super(name, contact);
        this.limit = limit;
        this.balance = 0;
    }

    public double getCreditLimit() { return limit; }
    public double getOutstandingBalance() { return balance; }

    public double applyDiscount(double amount) {
        return amount * 0.85;
    }

    public boolean hasSufficientCredit(double cost) {
        if (balance + cost <= limit) {
            return true;
        } else {
            return false;
        }
    }
}