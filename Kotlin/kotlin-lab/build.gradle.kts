plugins {
    kotlin("jvm") version "1.6.10"
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
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}
