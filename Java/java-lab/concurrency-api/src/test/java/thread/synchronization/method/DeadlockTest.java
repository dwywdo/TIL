package thread.synchronization.method;

import org.junit.jupiter.api.Test;

import thread.synchronization.deadlock.Friend;

public class DeadlockTest {
    @Test
    void deadLockTest() {
        Friend alphones = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");

        new Thread(new Runnable() {
            @Override
            public void run() {
                alphones.bow(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                gaston.bow(alphones);
            }
        }).start();
    }
}
