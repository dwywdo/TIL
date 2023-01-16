package me.dwywdo.designpattern.creational.sample;

import java.util.Arrays;
import java.util.List;

public class Room extends MapSite {
    private int roomNumber;
    List<MapSite> sides;

    Room(int roomNo) {
        this.roomNumber = roomNo;
        this.sides = Arrays.asList(null, null, null, null);
    }

    int getRoomNumber() { return roomNumber; }

    MapSite getSide(Direction direction) {
        return sides.get(direction.ordinal());
    }

    void setSide(Direction direction, MapSite mapSite) {
        sides.set(direction.ordinal(), mapSite);
    }

    @Override
    void enter() {
        System.out.println("Enter from Room " + roomNumber);
    }
}
