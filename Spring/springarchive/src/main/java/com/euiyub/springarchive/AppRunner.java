package com.euiyub.springarchive;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AppRunner implements ApplicationRunner {

    /**
     * We do not usually create customized validator
     * If you use Spring Boot > 2.0.5,
     * LocalValidatorFactoryBean (One of Validator) by Spring is automatically registered by Spring Boot
     * Web / WebFlux starters do not bring validation starter..from spring boot 2.3
     * You need to manually add dependency in your build system.
     * Otherwise you'll not be able to use @NotEmpty, @Size, etc.
     **/

    @Autowired
    Validator validator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        EventValidator eventValidator = new EventValidator();
        Event event = new Event(1);
        event.setLimit(-1);
        event.setEmail("aaa2");
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
            notempty.event.title
            notempty.title
            notempty.java.lang.String
            notempty
            Empty title is not allowed 1
            ===== error code =====
            notempty.event
            notempty
            Empty title is not allowed 2
         */

        System.out.println(validator.getClass());
        validator.validate(event, errors);
        System.out.println(errors.hasErrors());
        errors.getAllErrors().forEach(e -> {
            System.out.println("===== error code =====");
            Arrays.stream(e.getCodes()).forEach(System.out::println);
            System.out.println(e.getDefaultMessage());
        });

        /*
        ===== error code =====
            Min.event.limit
            Min.limit
            Min.java.lang.Integer
            Min
            must be greater than or equal to 0
            ===== error code =====
            Email.event.email
            Email.email
            Email.java.lang.String
            Email
            must be a well-formed email address
            ===== error code =====
            NotEmpty.event.title
            NotEmpty.title
            NotEmpty.java.lang.String
            NotEmpty
            must not be empty
         */
    }
}
