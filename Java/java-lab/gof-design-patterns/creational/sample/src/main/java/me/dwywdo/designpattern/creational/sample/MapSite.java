package me.dwywdo.designpattern.creational.sample;

public abstract class MapSite {
    /**
     * If you enter a room, then your location changes
     * If you try to enter a door,
     * - If the door is open, you go into the next room
     * - If the door is closed, then you hurt your nose
     */
    abstract void enter();
}
