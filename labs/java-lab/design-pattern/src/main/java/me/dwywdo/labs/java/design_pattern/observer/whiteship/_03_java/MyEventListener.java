package me.dwywdo.labs.java.design_pattern.observer.whiteship._03_java;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {

    @EventListener(MyEvent.class)
    public void onApplicationEvent(MyEvent event) {
        System.out.println("event.getMyCustomMessage() = " + event.getMyCustomMessage());
    }
}
