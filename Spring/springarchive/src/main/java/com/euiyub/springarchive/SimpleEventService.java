package com.euiyub.springarchive;

import org.springframework.stereotype.Service;

/**
 * Real Subject
 */
@Service
public class SimpleEventService implements EventService {

    @PerfLogging
    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Created an Event");
    }

    @PerfLogging
    @Override
    public void publishEvent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Published an Event");
    }
    @Override
    public void deleteEvent() {
        System.out.println("Deleted an event");
    }
}
