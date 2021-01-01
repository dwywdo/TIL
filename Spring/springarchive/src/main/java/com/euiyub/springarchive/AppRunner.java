package com.euiyub.springarchive;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@Component
public class AppRunner implements ApplicationRunner {



    @Override
    public void run(ApplicationArguments args) throws Exception {
        Event event = new Event();
        EventValidator eventValidator = new EventValidator();
        Errors errors = new BeanPropertyBindingResult(event, "event");
        // event is target instance
        // "event" is just objectName
        // This is a rare case that we use this classes (BeanPropertyBindingResult, etc) on Spring MVC
        // Spring MVC auto-creates Errors

        eventValidator.validate(event, errors);

        System.out.println(errors.hasErrors());
        errors.getAllErrors().forEach(e -> {
            System.out.println("===== error code =====");
            Arrays.stream(e.getCodes()).forEach(System.out::println);
            System.out.println(e.getDefaultMessage());
        });

        /*
         ===== error code =====
          notempty.event.title -> automatic
          notempty.title -> automatic
          notempty.java.lang.String -> automatic
          notempty
          Empty title is not allowed
         */
    }
}
