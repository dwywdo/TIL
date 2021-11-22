package thread;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ThreadCreationTest {
    @Test
    void createThreadWithRunnable() {
        new Thread(new HelloRunnable()).start();
    }

    @Test
    void createThreadWithInheritance() {
        new HelloThread().start();
    }

    @Test
    void threadSleepTest() throws InterruptedException {
        List<String> importantInfo = Arrays.asList(
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        );

        for (String info: importantInfo) {
            Thread.sleep(4000);
            System.out.println(info);
        }
    }
}
