package whiteship.designpattern.creational.abstractfactory;

import org.junit.jupiter.api.Test;

import me.whiteship.designpattern.creational.abstractfactory.Ship;
import me.whiteship.designpattern.creational.abstractfactory.ShipPartsFactory;
import me.whiteship.designpattern.creational.abstractfactory.WhitePartsProFactory;
import me.whiteship.designpattern.creational.abstractfactory.WhiteShip;

public class ShipTest {
    private ShipPartsFactory shipPartsFactory;

    @Test
    public void createShip() {
        // To change parts w/ pro line, shipPartsFactory = new WhitePartsProFactory();
        shipPartsFactory = new WhitePartsProFactory();

        Ship ship = new WhiteShip();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
    }
}
