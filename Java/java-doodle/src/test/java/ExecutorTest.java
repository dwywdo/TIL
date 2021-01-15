import static utils.MyRunnable.getRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import utils.ThreadLogger;

public class ExecutorTest {
    private final String mainName = "Main";
    private final String taskName = "Task";
    @Test
    public void basicExecutorService() {

        ThreadLogger.startLog(mainName);
        final ExecutorService exService = Executors.newSingleThreadExecutor();
        exService.execute(() -> {
            ThreadLogger.startLog(taskName);
            ThreadLogger.log(taskName);
            ThreadLogger.endLog(taskName);
        });
        ThreadLogger.endLog(mainName);
    }

    @Test
    public void submitToExecutorService() {
        ThreadLogger.startLog(mainName);
        final ExecutorService exService = Executors.newSingleThreadExecutor();
        exService.submit(() -> {
            ThreadLogger.startLog(taskName);
            ThreadLogger.log(taskName);
            ThreadLogger.endLog(taskName);
        });
        ThreadLogger.endLog(mainName);
        exService.shutdown();
    }

    @Test
    public void setPoolSizeOfExecutorService() throws InterruptedException {
        ThreadLogger.startLog(mainName);
        final ExecutorService exService = Executors.newFixedThreadPool(2);
        exService.submit(getRunnable("1"));
        exService.submit(getRunnable("2"));
        exService.submit(getRunnable("3"));
        exService.submit(getRunnable("4"));
        exService.submit(getRunnable("5"));
        Thread.sleep(5000L);
        ThreadLogger.endLog(mainName);
        exService.shutdown();
    }

    @Test
    public void scheduledExecutorService() throws InterruptedException {
        ThreadLogger.startLog(mainName);
        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // Execute once at schedule
        scheduledExecutorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        // Execute like cron
//        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
        Thread.sleep(10000L);
        ThreadLogger.endLog(mainName);
    }
}
