package thread.synchronization.method;

public class NoSyncRunnableA implements Runnable {
    private final Counter counter;

    public NoSyncRunnableA(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();
    }

    private void printCounterValue() {
        System.out.println(
                System.currentTimeMillis() + "\tNoSyncRunnableA\t" + counter.value());
    }
}
