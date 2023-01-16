package me.dwywdo.designpattern.designpattern.abstractfactory;

import me.dwywdo.designpattern.creational.sample.Door;
import me.dwywdo.designpattern.creational.sample.Room;

public class DoorNeedingSpell extends Door {
    public DoorNeedingSpell(Room r1, Room r2) {
        super(r1, r2);
    }
}
