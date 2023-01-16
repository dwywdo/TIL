package me.dwywdo.designpattern.designpattern.abstractfactory;

import me.dwywdo.designpattern.creational.sample.Room;
import me.dwywdo.designpattern.creational.sample.Wall;

public class BombedMazeFactory extends MazeFactory {

    Wall makeWall() {
        return new BombedWall();
    }

    Room makeRoom(int roomNumber) {
        return new RoomWithBomb(roomNumber);
    }
}
