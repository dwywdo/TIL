package me.dwywdo.designpattern.creational.sample;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    List<Room> rooms;
    Maze() { rooms = new ArrayList<>(); }

    void addRoom(Room r) { rooms.add(r); }

    Room roomNo(int roomNumber) {
        return rooms.stream().filter(room -> room.getRoomNumber() == roomNumber).findFirst().orElseThrow(
                () -> new IllegalArgumentException("There's no room for specified room number " + roomNumber)
        );
    }
}
