package me.dwywdo.designpattern.creational.sample;

import java.util.Arrays;
import java.util.List;

public class Room extends MapSite {
    private int roomNumber;
    List<MapSite> sides;

    public Room(int roomNo) {
        this.roomNumber = roomNo;
        this.sides = Arrays.asList(null, null, null, null);
    }

    public int getRoomNumber() { return roomNumber; }

    public MapSite getSide(Direction direction) {
        return sides.get(direction.ordinal());
    }

    public void setSide(Direction direction, MapSite mapSite) {
        sides.set(direction.ordinal(), mapSite);
    }

    @Override
    public void enter() {
        System.out.println("Enter from Room " + roomNumber);
    }
}
