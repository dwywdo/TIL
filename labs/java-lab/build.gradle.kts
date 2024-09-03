plugins {
    application
    id("io.freefair.lombok") version "8.10" apply false
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

        // This dependencies are for logging
        implementation("ch.qos.logback:logback-classic:1.4.14")
        implementation("org.slf4j:log4j-over-slf4j:1.7.36")
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
