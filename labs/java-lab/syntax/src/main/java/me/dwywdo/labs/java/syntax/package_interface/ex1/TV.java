package me.dwywdo.labs.java.syntax.package_interface.ex1;

public interface TV {
    int MAX_VOLUME = 10;
    public static final int MIN_VOLUME = 10;

    public abstract void turnOn();
    void turnOff();
    void changeVolume();
    void changeChannel();
}
