import org.junit.Test;

import utils.MyThread;
import utils.ThreadLogger;

public class ThreadTest {

    private final String mainName = "Main";
    private final String taskName = "Task";

    @Test
    public void useMyThread() throws InterruptedException {
        final MyThread myThread = new MyThread();
        ThreadLogger.log(mainName);
        myThread.start();
        // Thread.sleep(1000L);
        ThreadLogger.log(taskName);
    }

    @Test
    public void giveRunnableToThread() throws InterruptedException {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLogger.log(taskName);
            }
        });
        ThreadLogger.log(mainName);
        thread.start();
        // Thread.sleep(1000L);
        ThreadLogger.log(taskName);
    }

    @Test
    public void useLambdaWithRunnable() {
        final Thread thread = new Thread(() -> {
            try {
                ThreadLogger.log(taskName);
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        ThreadLogger.log(mainName);

        thread.start();

        ThreadLogger.log(mainName);
    }

    @Test
    public void interruptAnotherThread() throws InterruptedException {
        final Thread thread = new Thread(() -> {
           while(true) {
               ThreadLogger.log(taskName);
               try {
                   Thread.sleep(1000L);
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   System.out.println("Exit!");
                   return;
               }
           }
        });
        ThreadLogger.log(mainName);
        thread.start();
        Thread.sleep(3500L);
        thread.interrupt();
    }

    @Test
    public void joinThread() {
        ThreadLogger.startLog(mainName);

        final Thread thread = new Thread(() -> {
            ThreadLogger.startLog(taskName);
            try {
                ThreadLogger.log(taskName);
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            } finally {
              ThreadLogger.endLog(taskName);
            }
        });

        thread.start();

        try {
            thread.join(); // Waiting above thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadLogger.endLog(mainName);
    }
}
