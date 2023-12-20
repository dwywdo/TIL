plugins {
    application
}

subprojects {
    plugins.apply("application")

    repositories {
        // Use Maven Central for resolving dependencies.
        mavenCentral()
    }

    dependencies {
        // Use JUnit Jupiter for testing.
        testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        // This dependency is used by the application.
        implementation("com.google.guava:guava:32.1.1-jre")
    }

// Apply a specific Java toolchain to ease working on different environments.
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }

    tasks.named<Test>("test") {
        // Use JUnit Platform for unit tests.
        useJUnitPlatform()
    }
}
