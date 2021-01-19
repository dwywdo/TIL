package me.dwywdo.springbootconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    /**
     * @Value("${test.name}")
    private String name;

    @Value("${test.age}")
    private int age;

    @Value("${test.fullName}")
    private String fullName;

     */
    @Autowired
    MyProperties myProperties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(myProperties.getName());
        System.out.println(myProperties.getAge());
        System.out.println(myProperties.getFullName());
        System.out.println(myProperties.getSessionTimeout());

        System.out.println(hello);
    }
}
