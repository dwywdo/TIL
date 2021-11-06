package thread.synchronization.method;

public class NoSyncRunnableB implements Runnable {
    private final Counter counter;

    public NoSyncRunnableB(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.decrement();
    }

    private void printCounterValue() {
        System.out.println(
                System.currentTimeMillis() + "\tNoSyncRunnableB\t" + counter.value());
    }
}
