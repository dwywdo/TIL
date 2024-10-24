package me.dwywdo.labs.java.design_pattern.observer.gof;

import java.time.LocalTime;

public class ClockTimer extends Subject implements Runnable {
    private int hour;
    private int minute;
    private int second;

    public ClockTimer() {
        updateCurrentTime();
    }

    public void tick() {
        updateCurrentTime();

        System.out.println("Clock Timer - Hour: " + hour + " Minute: " + minute + " Second: " + second);

        notifyObservers();
    }

    private void updateCurrentTime() {
        final LocalTime now = LocalTime.now();
        hour = now.getHour();
        minute = now.getMinute();
        second = now.getSecond();
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public void run() { tick(); }

    public void invokeTick(int numbers) throws InterruptedException {
        for (int i = 0; i < numbers; i++) {
            Thread.sleep(1000);
            run();
        }
    }
}
