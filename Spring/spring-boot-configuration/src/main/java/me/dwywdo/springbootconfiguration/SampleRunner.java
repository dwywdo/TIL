package me.dwywdo.springbootconfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(SampleRunner.class);

    @Autowired
    MyProperties myProperties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("=================");
        logger.debug(hello);
        logger.debug(myProperties.getName());
        logger.debug(myProperties.getFullName());
        logger.debug("=================");

//        System.out.println(myProperties.getName());
//        System.out.println(myProperties.getAge());
//        System.out.println(myProperties.getFullName());
//        System.out.println(myProperties.getSessionTimeout());
//        System.out.println(hello);
    }
}
