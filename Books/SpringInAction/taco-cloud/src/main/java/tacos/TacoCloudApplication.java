package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootConfiguration: Denotes this class as a configuration class. Specialized form of @Configuration.
 * @EnableAutoConfiguration: Enable Spring Boot Automatic Configuration. (Automatically configure any components that you'll need.
 * @ComponentScan: Enable component scanning. It lets you declare other classes with annotations like @Component, @Controller, @Service, and others.
 */
@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        /**
         * This call of static method 'run' is actual bootstrapping of the application, creating Spring Application Context
         * Parameters?
         * 1. primarySource: Configuration class.
         * 2. args: Command-Line Arguments.
         */
        SpringApplication.run(TacoCloudApplication.class, args);
    }

}
