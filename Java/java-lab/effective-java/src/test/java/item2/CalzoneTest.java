package item2;

import static item2.NyPizza.Size.SMALL;
import static item2.Pizza.Topping.HAM;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalzoneTest {
    @Test
    void createCalzoneUsingBuilder() {
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM)
                .sauceInside()
                .build();
    }
}
