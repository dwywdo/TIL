package me.dwywdo;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class InstantTest {
    @Test
    void instantEpochTime() {
        Instant epoch = Instant.EPOCH;
        System.out.println("epoch = " + epoch);

        Instant epochInFuture = Instant.ofEpochSecond(1_000_000_000);
        System.out.println("epochInFuture = " + epochInFuture);

        Instant epochInPast = Instant.ofEpochSecond(-1_000_000_000);
        System.out.println("epochInPast = " + epochInPast);
    }

    @Test
    void getCurrentTimestamp() {
        Instant current = Instant.now();
        System.out.println("Current Instant: " + current);

        long epochSecond = current.getEpochSecond();
        System.out.println("Current Timestamp in seconds = " + epochSecond);

        long epochMilli = current.toEpochMilli();
        System.out.println("Current Timestamp in milli seconds = " + epochMilli);
    }

    @Test
    void getMaxTimestamp() {
        Instant maxInstant = Instant.MAX;
        long maxEpochSeconds = maxInstant.getEpochSecond();
        /**
         * 31556889864403199 = Epoch Seconds for Max Instant
         * 9223372036854775807 << 31556889864403199000 (MAX Seconds * 1000) -> long overflow
         *
         * Range of long type variable -9223372036854775808 ~ 9223372036854775807
         * Seconds -> Milliseconds operation (Multiplying 1000) should be between above range
         *
         * (Lower bound for dividing 1000) 9223372036854775807 -> 9223372036854775000
         * 9223372036854775000 / 1000 = 9223372036854775L
         *
         * Candidate for value: 9223372036854775L
         */
        System.out.println("Maxium epoch seconds = " + maxEpochSeconds);
        Instant pseudoMaxInstant = Instant.ofEpochSecond(9223372036854775L);
        long milliseconds = pseudoMaxInstant.toEpochMilli();
        System.out.println("Pseudo max epochMillis = " + milliseconds);
        System.out.println("Pseudo max Instant = " + pseudoMaxInstant);
    }
}
