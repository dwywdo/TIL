package me.dwywdo.labs.java.design_pattern.observer.gof;

import org.junit.jupiter.api.Test;

public class ClockTimerTest {
    static final ClockTimer clockTimer = new ClockTimer();
    static DigitalClock digitalClock = new DigitalClock(clockTimer);
    static AnalogClock analogClock = new AnalogClock(clockTimer);

    @Test
    void testClockTimer() throws InterruptedException {
        clockTimer.invokeTick(10);
    }
}
