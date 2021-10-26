package item2;

import static item2.NyPizza.Size.SMALL;
import static item2.Pizza.Topping.HAM;
import static item2.Pizza.Topping.ONION;
import static item2.Pizza.Topping.SAUSAGE;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NyPizzaTest {
    @Test
    void createNyPizzaUsingBuilder() {
        NyPizza nyPizza = new NyPizza.Builder(SMALL)
                .addTopping(HAM)
                .addTopping(SAUSAGE)
                .addTopping(ONION)
                .build();
    }
}
