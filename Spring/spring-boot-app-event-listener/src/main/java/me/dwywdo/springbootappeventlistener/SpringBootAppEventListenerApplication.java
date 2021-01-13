package me.dwywdo.springbootappeventlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAppEventListenerApplication {

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(SpringBootAppEventListenerApplication.class);
        app.addListeners(new ContextBeforeEventListener());
        app.run(args);
    }

}
