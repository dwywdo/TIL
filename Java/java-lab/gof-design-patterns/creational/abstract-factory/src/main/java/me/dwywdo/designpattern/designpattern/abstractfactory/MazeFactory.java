package me.dwywdo.designpattern.designpattern.abstractfactory;

import me.dwywdo.designpattern.creational.sample.Door;
import me.dwywdo.designpattern.creational.sample.Maze;
import me.dwywdo.designpattern.creational.sample.Room;
import me.dwywdo.designpattern.creational.sample.Wall;

public class MazeFactory {
    public MazeFactory() {}

    Maze makeMaze() { return new Maze(); }

    Wall makeWall() { return new Wall(); }

    Room makeRoom(int roomNumber) {
        return new Room(roomNumber);
    }

    Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
}
