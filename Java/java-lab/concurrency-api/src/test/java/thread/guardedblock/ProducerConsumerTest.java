package thread.guardedblock;

import org.junit.jupiter.api.Test;

public class ProducerConsumerTest {
    @Test
    void producerConsumerTest() throws InterruptedException {
        Drop drop = new Drop();
        new Thread(new Producer(drop)).start();
        new Thread(new Consumer(drop)).start();

        Thread.sleep(1000000);
    }

}
