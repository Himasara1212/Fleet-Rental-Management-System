package lk.iise.fleet;

public abstract class Customer implements Reportable {
    private static int counter = 1000;

    private String customerId;
    private String fullName;
    private String contactNumber;

    public Customer(String fullName, String contactNumber) {
        this.customerId = "C-" + (++counter);
        this.fullName = fullName;
        this.contactNumber = contactNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public abstract double applyDiscount(double subtotal);

    @Override
    public String getSummary() {
        return customerId + " - " + fullName;
    }

    @Override
    public String getReportCategory() {
        return "CUSTOMER";
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId +
                ", Name: " + fullName +
                ", Contact: " + contactNumber;
    }
}