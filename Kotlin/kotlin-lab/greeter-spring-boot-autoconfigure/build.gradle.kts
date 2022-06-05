
dependencies {
    implementation("org.springframework.boot:spring-boot:2.6.3")
    implementation("org.springframework.boot:spring-boot-autoconfigure:2.6.3")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.6.3")

    api(project(":greeter-library"))

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.3")
}

