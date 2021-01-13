package me.dwywdo.springbootappeventlistener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;


@Component
public class ArgumentPrinter {

    public ArgumentPrinter(ApplicationArguments arguments) {
        System.out.println("foo: " + arguments.containsOption("foo"));
        System.out.println("bar: " + arguments.containsOption("bar"));
    }
}
