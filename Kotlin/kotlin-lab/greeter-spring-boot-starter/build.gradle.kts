dependencies {
    implementation("org.springframework.boot:spring-boot-starter:2.6.3")

    api(project(":greeter-spring-boot-autoconfigure"))
    api(project(":greeter-library"))

    testImplementation("org.springframework.boot:spring-boot-starter:2.6.3")
}
