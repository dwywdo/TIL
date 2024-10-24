package me.dwywdo.labs.java.design_pattern.observer.gof;

public interface ISubject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
