import io.freefair.gradle.plugins.aspectj.WeavingSourceSet

plugins {
    id("io.freefair.aspectj.post-compile-weaving") version "8.0.1"
}

dependencies {
    implementation("org.aspectj:aspectjrt:1.9.19")
    runtimeOnly("org.aspectj:aspectjweaver:1.9.19")
}
