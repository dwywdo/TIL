package me.dwywdo.labs.java.syntax.package_interface.ex5_field_conflict;

import org.junit.jupiter.api.Test;

public class FinalFieldTest {
    @Test
    void fieldsTest() {
        final Tulip t = new Tulip();
        System.out.println("Tulip: t.ex = " + t.ex);

        final IFlower iFlow = new Tulip();
        System.out.println("a.ex = " + iFlow.ex); // NG
        System.out.println("IFlower.ex = " + IFlower.ex);

        final IPlant iPlant = new Tulip();
        System.out.println("iPlant.ex = " + iPlant.ex);// NG
        System.out.println("IPlant.ex = " + IPlant.ex);
    }
}
