package utils;

public class MyThread extends Thread {
    @Override
    public void run() {
        ThreadLogger.log("Task");
    }
}
