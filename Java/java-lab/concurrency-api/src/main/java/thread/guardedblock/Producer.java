package thread.guardedblock;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        List<String> importantInfos = Arrays.asList(
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        );
        Random random = new Random();
        for(String info : importantInfos) {
            drop.put(info);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }
}
