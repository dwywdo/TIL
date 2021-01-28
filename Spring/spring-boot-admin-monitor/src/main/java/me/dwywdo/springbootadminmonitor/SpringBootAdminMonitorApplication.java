package me.dwywdo.springbootadminmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminMonitorApplication.class, args);
    }

}
