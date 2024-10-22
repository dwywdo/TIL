package me.dwywdo.labs.java.syntax.package_interface.ex4_polymorph;

import org.junit.jupiter.api.Test;

public class PolymorphTest {
    @Test
    void testPolymorph() {
        final Controllable[] o = { new MyObject(), new MyObject() };
        o[0].change();
        o[0].power(true);

        final Changable changable = new MyObject();
        final Powerable powerable = new MyObject();
        changable.change();
        powerable.power(true);
    }
}
