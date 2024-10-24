package me.dwywdo.labs.java.design_pattern.observer.gof;

import java.util.ArrayList;
import java.util.List;

// Observable에 대응
public abstract class Subject implements ISubject {
    private List<Observer> observers;

    protected Subject() {
        observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
