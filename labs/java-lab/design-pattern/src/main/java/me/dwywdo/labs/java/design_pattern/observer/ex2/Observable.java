package me.dwywdo.labs.java.design_pattern.observer.ex2;

public interface Observable {
    public void addObserver(Observer observer);
    public void deleteObserver(Observer observer);
    public void notifyObservers();
}
