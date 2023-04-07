import io.freefair.gradle.plugins.aspectj.WeavingSourceSet

plugins {
    java
    id("io.freefair.aspectj.post-compile-weaving") version "8.0.1"
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.4.6")
    implementation("org.aspectj:aspectjrt:1.9.19")
    runtimeOnly("org.aspectj:aspectjweaver:1.9.19")
}
