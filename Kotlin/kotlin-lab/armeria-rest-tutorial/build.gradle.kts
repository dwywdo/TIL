dependencies {
    implementation("com.linecorp.armeria:armeria:1.13.2")

    api("io.github.microutils:kotlin-logging:1.12.5")
    api("org.slf4j:slf4j-api:1.7.5")
    api("org.slf4j:slf4j-simple:1.7.5")
}

/**
tasks.withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
    options.encoding = "UTF-8"
    options.isDebug = true
    val compilerArgs = options.compilerArgs
    compilerArgs.add("-parameters")
}
*/
