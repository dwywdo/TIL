package me.dwywdo.labs.java.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.dwywdo.labs.java.exception.BadRequestException;

public class App {

    public static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            log.info("Calling retriable method");
            final Example example = new Example();
            final int result = example.retryMethod("retry");
//            int result = example.retryMethod("fail");
//            int result = example.retryMethod("pass");
            log.info("Printing result - {}", result);
        } catch (BadRequestException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
