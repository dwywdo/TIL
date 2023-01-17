package me.whiteship.designpattern.creational.abstractfactory;

/**
 * Abstract Factory에서 제공하는 Part들은 일련의 규약(인터페이스)을 지킨다
 */
public class WhiteShipPartsFactory implements ShipPartsFactory {

    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}
