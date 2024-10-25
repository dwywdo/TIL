package me.dwywdo.labs.java.design_pattern.observer.whiteship._03_java;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProppertyChangeExample {
    static class User implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            System.out.println("evt.getOldValue() = " + evt.getOldValue());
            System.out.println("evt.getNewValue() = " + evt.getNewValue());
        }
    }

    static class Subject {
        PropertyChangeSupport support = new PropertyChangeSupport(this);

        public void addObserver(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }

        public void removeObserver(PropertyChangeListener listener) {
            support.removePropertyChangeListener(listener);
        }

        public void add(String message) {
            support.firePropertyChange("eventName", null, message);
        }
    }

    public static void main(String[] args) {
        final Subject subject = new Subject();
        final User observer = new User();
        subject.addObserver(observer);
        subject.add("Java PCL Example");
        subject.removeObserver(observer);
        subject.add("You can't see this message");
    }
}
