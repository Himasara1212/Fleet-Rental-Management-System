package lk.iise.fleet;

public class OverdueReminderWorker implements Runnable {
    private FleetManager fm;

    public OverdueReminderWorker(FleetManager fm) {
        this.fm = fm;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                fm.checkOverdueAgreements();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}