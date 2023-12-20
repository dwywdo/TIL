application {
    // Define the main class for the application.
    // mainClass.set("armeria.lab.App")
}

plugins {
    id("io.spring.dependency-management") version "1.1.4"
}

dependencyManagement {
    imports {
        mavenBom("com.linecorp.armeria:armeria-bom:1.26.4")
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.7.12")
    }
}

dependencies {
    implementation("com.linecorp.armeria:armeria-spring-boot2-starter")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }

    implementation("com.linecorp.armeria:armeria-spring-boot2-actuator-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")

    implementation("com.linecorp.armeria:armeria-thrift0.18")

    implementation(project(":spring-boot-thrift:protocol"))
}
