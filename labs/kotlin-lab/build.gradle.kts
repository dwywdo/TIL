plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.8.22" apply false
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

subprojects {
    plugins.apply("org.jetbrains.kotlin.jvm")
    plugins.apply("application")

    repositories {
        // Use Maven Central for resolving dependencies.
        mavenCentral()
    }

    application {
        mainClass.set("dwywdo.kotlin.lab.MainKt")
    }

    dependencies {
        // Use the Kotlin JUnit 5 integration.
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

        // Use the JUnit 5 integration.
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")

        // This dependency is used by the application.
        implementation("com.google.guava:guava:31.1-jre")
    }

    tasks.named<Test>("test") {
        // Use JUnit Platform for unit tests.
        useJUnitPlatform()
    }
}

task("createSubproject") {
    doLast {
        val spName = project.findProperty("spName")?.toString() ?: run {
            throw GradleException("Required parameter 'spName' is not provided")
        }

        println("Subproject Name: $spName")
        val spDir = file(spName)

        if (!spDir.exists()) {
            println("Creating subproject: $spName")

            project.copy {
                from("sample_subproject")
                into(spDir)
            }

            val settingsFile = file("settings.gradle.kts")
            settingsFile.appendText("include(\"$spName\")\n")
        } else {
            println("Failed to create subproject $spName: It already exists")
        }
    }
}
