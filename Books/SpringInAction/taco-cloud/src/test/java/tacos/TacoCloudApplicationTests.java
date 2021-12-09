package tacos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This annotation tells JUnit to bootstrap the test with Spring Boot capabilities
 * Just like @SpringBootApplication, @SpringBootTest is also composite annotation
 * @ExtendWith({SpringExtension.class}) is used to add Spring testing capabilities to JUnit 5
 */
@SpringBootTest
class TacoCloudApplicationTests {

    @Test
    void contextLoads() {
    }

}
