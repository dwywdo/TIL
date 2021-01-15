package utils;

import java.time.LocalDateTime;

public final class ThreadLogger {

    public static void log(String str) {
        System.out.println(LocalDateTime.now() + " "  + str + " Thread here: " + Thread.currentThread().getName());
    }
    public static void startLog(String str) {
        System.out.println(LocalDateTime.now() + " "  + str + " Thread started: " + Thread.currentThread().getName());
    }
    public static void endLog(String str) {
        System.out.println(LocalDateTime.now() + " "  + str + " Thread finished: " + Thread.currentThread().getName());
    }

    private ThreadLogger() {}
}
