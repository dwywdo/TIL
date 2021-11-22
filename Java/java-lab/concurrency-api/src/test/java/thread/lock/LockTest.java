package thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

public class LockTest {
    @Test
    void lockTest() {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        new Thread(new BowLoop(alphonse, gaston)).start();
        new Thread(new BowLoop(gaston, alphonse)).start();
    }
}
