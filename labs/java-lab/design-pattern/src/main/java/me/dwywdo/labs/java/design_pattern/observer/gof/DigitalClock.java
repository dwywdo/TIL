package me.dwywdo.labs.java.design_pattern.observer.gof;

public class DigitalClock implements Observer {
    ClockTimer clockTimer;

    public DigitalClock(ClockTimer clockTimer) {
        this.clockTimer = clockTimer;
        clockTimer.attach(this);
    }

    @Override
    public void update(ISubject subject) {
        if (clockTimer == subject) {
            printCurrentTime((ClockTimer) subject);
        }
    }

    private static void printCurrentTime(ClockTimer clockTimer) {
        System.out.println("This is Digital Clock");
        System.out.println("clockTimer.getHour() = " + clockTimer.getHour());
        System.out.println("clockTimer.getMinute() = " + clockTimer.getMinute());
        System.out.println("clockTimer.getSecond() = " + clockTimer.getSecond());
        System.out.println();
    }
}
