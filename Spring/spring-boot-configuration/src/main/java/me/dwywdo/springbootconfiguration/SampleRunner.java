package me.dwywdo.springbootconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Value("${test.name}")
    private String name;

    @Value("${test.age}")
    private int age;

    @Value("${test.fullName}")
    private String fullName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(name);
        System.out.println(age);
        System.out.println(fullName);
    }
}
