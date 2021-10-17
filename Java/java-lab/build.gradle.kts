plugins {
    java
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")

    group = "me.dwywdo"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}
