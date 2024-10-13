plugins {
    war
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat") // from war plugin

    // For JSP (Start)
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper")
    implementation("jakarta.servlet:jakarta.servlet-api") // Spring Boot >= 3.0
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api") // Spring Boot >= 3.0
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl") // Spring Boot >= 3.0
    // For JSP (End)
}
