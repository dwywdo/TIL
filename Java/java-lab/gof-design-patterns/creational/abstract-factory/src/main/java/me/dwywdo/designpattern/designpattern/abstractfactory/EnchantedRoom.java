package me.dwywdo.designpattern.designpattern.abstractfactory;

import me.dwywdo.designpattern.creational.sample.Room;

public class EnchantedRoom extends Room {
    public EnchantedRoom(int roomNumber, Spell spell) {
        super(roomNumber);
    }
}
