package me.dwywdo.labs.java.design_pattern.observer.whiteship._01_before;

import org.junit.jupiter.api.Test;

class BeforeTest {
    @Test
    void beforeTest() {
        final ChatServer chatServer = new ChatServer();
        final User user1 = new User(chatServer);

        user1.sendMessage("Design Pattern", "This is Observer Pattern");
        user1.sendMessage("2021 LoL Worlds", "LCK Go!");

        final User user2 = new User(chatServer);
        System.out.println("user2.getMessage(\"Design Pattern\"); = " + user2.getMessage("Design Pattern"));

        user1.sendMessage("Design Pattern", "Going over examples..");
        System.out.println("user2.getMessage(\"Design Pattern\"); = " + user2.getMessage("Design Pattern"));
    }
}
