package thread.synchronization.method;

public class SyncRunnableB implements Runnable {
    private final SynchronizedCounter synchronizedCounter;

    public SyncRunnableB(SynchronizedCounter synchronizedCounter) {
        this.synchronizedCounter = synchronizedCounter;
    }

    @Override
    public void run() {
        synchronizedCounter.decrement();
    }

    private void printCounterValue() {
        System.out.println(
                System.currentTimeMillis() + "\tSyncRunnableB\t" + synchronizedCounter.value());
    }
}
