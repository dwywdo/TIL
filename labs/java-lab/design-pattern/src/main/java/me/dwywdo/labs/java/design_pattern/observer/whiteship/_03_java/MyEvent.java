package me.dwywdo.labs.java.design_pattern.observer.whiteship._03_java;

public class MyEvent {
    private String myCustomMessage;

    public MyEvent(String message) {
        this.myCustomMessage = message;
    }

    public String getMyCustomMessage() {
        return myCustomMessage;
    }
}
