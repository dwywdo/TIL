plugins {
    kotlin("jvm") version "1.5.31"
}


repositories {
    mavenCentral()
}

subprojects {
    apply(plugin="kotlin")

    group = "me.dwywdo"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }

    dependencies {
        implementation(kotlin("stdlib"))
    }
}
