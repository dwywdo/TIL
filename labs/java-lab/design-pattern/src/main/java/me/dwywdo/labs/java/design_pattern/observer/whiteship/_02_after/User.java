package me.dwywdo.labs.java.design_pattern.observer.whiteship._02_after;

public class User implements Subscriber {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void handleMessage(String message) {
        System.out.println("message = " + message);
    }

    public String getName() {
        return name;
    }
}
