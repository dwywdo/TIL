package me.whiteship.designpattern.creational.abstractfactory;

public interface ShipPartsFactory {
    Anchor createAnchor();
    Wheel createWheel();
}
