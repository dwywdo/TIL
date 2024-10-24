package me.dwywdo.labs.java.design_pattern.observer.whiteship._02_after;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {
    private Map<String, List<Subscriber>> subscribers = new HashMap<>();

    public void register(String subject, Subscriber subscriber) {
        if (subscribers.containsKey(subject)) {
            subscribers.get(subject).add(subscriber);
        } else {
            final List<Subscriber> list = new ArrayList<>();
            list.add(subscriber);
            subscribers.put(subject, list);
        }
    }

    public void unregister(String subject, Subscriber subscriber) {
        if (subscribers.containsKey(subject)) {
            subscribers.get(subject).remove(subscriber);
        }
    }

    public void sendMessage(User user, String subject, String message) {
        if (this.subscribers.containsKey(subject)) {
            final String userMessage = user.getName() + ": " + message;
            this.subscribers.get(subject).forEach(
                    subscriber -> subscriber.handleMessage(userMessage)
            );
        }
    }
}
