package me.dwywdo.labs.java.aop;

import me.dwywdo.labs.java.aop.aspect.RetryMethod;
import me.dwywdo.labs.java.exception.BadRequestException;

public class Example {
    public static int count = 0;

    @RetryMethod(
            retries = 5,
            interval = 2
    )
    public int retryMethod(String test) throws BadRequestException, Exception {

        if (test == "retry") {
            if (count < 10) {
                count++;
                throw new BadRequestException("Bad Request Received");
            }
            return 0;
        } else if (test == "fail") {
            throw new Exception("Failed to execute method");
        }
        return 0;
    }
}
