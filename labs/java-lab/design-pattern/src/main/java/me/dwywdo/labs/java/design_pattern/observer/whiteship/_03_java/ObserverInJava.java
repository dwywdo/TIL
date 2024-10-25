package me.dwywdo.labs.java.design_pattern.observer.whiteship._03_java;

import java.util.Observable;
import java.util.Observer;

public class ObserverInJava {

    static class User implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("arg = " + arg);
        }
    }

    static class Subject extends Observable {
        public void add(String message) {
            setChanged();
            notifyObservers(message);

            setChanged();
            notifyObservers(message);
        }
    }

    public static void main(String[] args) {
        final Subject subject = new Subject();
        final User user = new User();
        subject.addObserver(user);
        subject.add("Hello Java, Observer");
    }
}
