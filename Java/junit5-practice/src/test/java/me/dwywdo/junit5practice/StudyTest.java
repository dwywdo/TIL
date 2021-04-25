package me.dwywdo.junit5practice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StudyTest {

    @Test
    void create_new_study_with_initial_values() {
        final Study study = new Study(10);
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT,
                     study.getStatus(),
                     () -> "If you create a new study instance, status should be DRAFT");
        assertTrue(study.getLimit() > 0, "The number of study members should not be 0");
    }

    @Test
    void create_new_study_under_local_env() {
        final String requiredTestEnvAsString = "LOCAL";
        final String currentTestEnvAsString = System.getenv("TEST_ENV");
        assumeTrue(requiredTestEnvAsString.equalsIgnoreCase(currentTestEnvAsString));
        // Do Some Test
        System.out.println("Test is running");
    }

    @Test
    void create_new_study_under_local_env_using_assumingThat() {
        final String localTestEnvAsString = "LOCAL";
        final String ciTestEnvAsString = "CI";
        final String currentTestEnvAsString = System.getenv("TEST_ENV");

        assumingThat(localTestEnvAsString.equalsIgnoreCase(currentTestEnvAsString), () -> {
            System.out.println("It's a local environment");
        });

        assumingThat(ciTestEnvAsString.equalsIgnoreCase(currentTestEnvAsString), () -> {
            System.out.println("It's a ci environment");
        });
    }

    @Test
    void create_new_study_under_assumption() {
        final String testEnvAsString = System.getenv("TEST_ENV");
        System.out.println(testEnvAsString);
        assumeTrue("LOCAL".equalsIgnoreCase(testEnvAsString));

        assumingThat("LOCAL".equalsIgnoreCase(testEnvAsString), () -> {
            final Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

        final Study study = new Study(10);
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT,
                     study.getStatus(),
                     () -> "If you create a new study instance, status should be DRAFT");
        assertTrue(study.getLimit() > 0, "The number of study members should not be 0");
    }

    @Test
    void create_new_study_with_negative_limit() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                                () -> new Study(-10));
        final String message = exception.getMessage();
        assertEquals(message, "limit should not be negative");
    }

    @Test
    void create_new_study_with_timeout() {
//        assertTimeout(Duration.ofSeconds(10), () -> new Study(10));
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    @Test
    @Disabled
    void disabled_test_example() {
        System.out.println("create3");
    }

    @Test
    @DisplayName("Fast test 1")
    @Tag("fast")
    void fast_test_1() {
        System.out.println("I'm fast test sample");
    }

    @Test
    @DisplayName("Slow test 1")
    @Tag("slow")
    void slow_test_1() {
        System.out.println("I'm slow test sample");
    }

    @BeforeAll
    static void setUp() {
        System.out.println("BeforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach");
    }
}
