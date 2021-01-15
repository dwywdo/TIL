package utils;

public final class MyRunnable {
    public static Runnable getRunnable(String str) {
        return () -> {
          ThreadLogger.startLog(str);
          ThreadLogger.log(str);
          ThreadLogger.endLog(str);
        };
    }

    private MyRunnable() {}
}
