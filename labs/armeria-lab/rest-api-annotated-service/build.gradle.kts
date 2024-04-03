application {
    // Define the main class for the application.
    // mainClass.set("armeria.lab.App")
}

plugins.apply("java")
plugins.apply("idea")
plugins.apply("eclipse")

dependencies {
    implementation("com.linecorp.armeria:armeria:1.27.3")

    runtimeOnly("ch.qos.logback:logback-classic:1.4.14")
    runtimeOnly("org.slf4j:log4j-over-slf4j:1.7.36")
}
