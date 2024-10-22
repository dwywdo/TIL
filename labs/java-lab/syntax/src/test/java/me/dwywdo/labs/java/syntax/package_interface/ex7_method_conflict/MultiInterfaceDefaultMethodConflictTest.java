package me.dwywdo.labs.java.syntax.package_interface.ex7_method_conflict;

import org.junit.jupiter.api.Test;

public class MultiInterfaceDefaultMethodConflictTest {
    @Test
    void defaultMethodConflict() {
        final MultiInterface m1 = new MultiInterface();
        m1.styleSame();
    }
}
