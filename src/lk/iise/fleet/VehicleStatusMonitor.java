package lk.iise.fleet;

public class VehicleStatusMonitor implements Runnable {
    private FleetManager fm;

    public VehicleStatusMonitor(FleetManager fm) {
        this.fm = fm;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                fm.printFleetStatus();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}