package me.dwywdo.labs.java.supertype_token;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringTypeReferenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringTypeReferenceApplication.class, args);
    }

    @RestController
    public static class MyController {
        @RequestMapping("/")
        public List<User> users() {
            return Arrays.asList(
                    new User("A"),
                    new User("B"),
                    new User("C")
            );
        }
    }

    public static class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        public User() {

        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User{" +
                   "name='" + name + '\'' +
                   '}';
        }
    }
}
