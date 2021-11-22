package thread.example;

import org.junit.jupiter.api.Test;

public class MessageLoopTest {
    @Test
    void simpleThreadTest() throws InterruptedException {
        long patience = 6000; // 1 hour

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        while(t.isAlive()) {
            threadMessage("Still waiting...");
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                t.join(); // ?
            }
        }
        threadMessage("Finally!");
    }

    private void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s\n", threadName, message);
    }
}
