package me.dwywdo.springbootconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// You can omit this
// @EnableConfigurationProperties(MyProperties.class)
public class SpringBootConfigurationApplication {

    /**
     * Second way for third-party configuration
     *
     @ConfigurationProperties("server")
     @Bean
     public ServerProperties serverProperties() {
        return new ServerProperties();
     }

    */

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigurationApplication.class, args);
    }

}
