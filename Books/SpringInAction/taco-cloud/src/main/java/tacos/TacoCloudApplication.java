package tacos;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;

/**
 * @SpringBootConfiguration: Denotes this class as a configuration class. Specialized form of @Configuration.
 * @EnableAutoConfiguration: Enable Spring Boot Automatic Configuration. (Automatically configure any components that you'll need.
 * @ComponentScan: Enable component scanning. It lets you declare other classes with annotations like @Component, @Controller, @Service, and others.
 */
@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        /**
         * This call of static method 'run' is actual bootstrapping of the application, creating Spring Application Context
         * Parameters?
         * 1. primarySource: Configuration class.
         * 2. args: Command-Line Arguments.
         */
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        };
    }
}
