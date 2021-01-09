package me.dwywdo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import you.dwywdo.MyTarget;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * If you manually create bean with same type (MyTarget),
     * Which bean is registered? Bean overrding is not available from Spring Boot 2.1
     * To make it work, Use @ConditonalOnMissingBean on auto-configuration
     * Error Message:
     * -------------------------------------------------------------------------------
     * Description:
     *
     * The bean 'myTarget', defined in class path resource [you/dwywdo/MyTargetConfiguration.class], could not be registered. A bean with that name has already been defined in me.dwywdo.Application and overriding is disabled.
     *
     * Action:
     *
     * Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
     *
     * - How to override easily?
     *   - You can override fields of bean with ConfigurationProperties & application.properties
     */

}
