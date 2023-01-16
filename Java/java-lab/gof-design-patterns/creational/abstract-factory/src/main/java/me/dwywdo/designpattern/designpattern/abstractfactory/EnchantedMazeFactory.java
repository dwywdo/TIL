package me.dwywdo.designpattern.designpattern.abstractfactory;

import me.dwywdo.designpattern.creational.sample.Door;
import me.dwywdo.designpattern.creational.sample.Maze;
import me.dwywdo.designpattern.creational.sample.Room;
import me.dwywdo.designpattern.creational.sample.Wall;

public class EnchantedMazeFactory extends MazeFactory {

    @Override
    Room makeRoom(int roomNumber) {
        return new EnchantedRoom(roomNumber, castSpell());
    }

    @Override
    Door makeDoor(Room r1, Room r2) {
        return new DoorNeedingSpell(r1, r2);
    }

    protected Spell castSpell() {
        return new Spell();
    }
}
