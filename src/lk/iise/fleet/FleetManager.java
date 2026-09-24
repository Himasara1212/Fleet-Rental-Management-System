package lk.iise.fleet;

import java.util.ArrayList;

public class FleetManager {


    public ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
    public ArrayList<Customer> customerList = new ArrayList<Customer>();
    public ArrayList<RentalAgreement> rentalList = new ArrayList<RentalAgreement>();

    public FleetManager() {
    }


    public void printFleetStatus() {
    }

    public void checkOverdueAgreements() {
    }

    public void setRentalEventListener(RentalEventListener l) {
    }
}