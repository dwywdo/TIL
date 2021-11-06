package thread.example;

import java.util.Arrays;
import java.util.List;

public class MessageLoop implements Runnable {
    @Override
    public void run() {
        List<String> importantInfo = Arrays.asList(
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy, too"
        );
        try {
            for (String info : importantInfo) {
                Thread.sleep(4000);
                printMessage(info);
            }
        } catch (InterruptedException e) {
            printMessage("I wasn't done!");
        }
    }

    private void printMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s\n", threadName, message);
    }
}
