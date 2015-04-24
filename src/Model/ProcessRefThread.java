package Model;

/**
 * Created by Roland on 22/04/2015.
 */
public class ProcessRefThread implements Runnable {
    public void run() {
        new ProcessReferrals();
    }
}
