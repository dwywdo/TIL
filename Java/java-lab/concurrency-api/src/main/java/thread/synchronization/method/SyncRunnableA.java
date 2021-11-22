package thread.synchronization.method;

public class SyncRunnableA implements Runnable {
    private final SynchronizedCounter synchronizedCounter;

    public SyncRunnableA(SynchronizedCounter synchronizedCounter) {
        this.synchronizedCounter = synchronizedCounter;
    }

    @Override
    public void run() {
        synchronizedCounter.increment();
    }

    private void printCounterValue() {
        System.out.println(
                System.currentTimeMillis() + "\tSyncRunnableA\t" + synchronizedCounter.value());
    }
}
