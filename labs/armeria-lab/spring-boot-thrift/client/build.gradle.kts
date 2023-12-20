plugins {
    id("io.spring.dependency-management") version "1.1.4"
}

dependencyManagement {
    imports {
        mavenBom("com.linecorp.armeria:armeria-bom:1.26.4")
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.7.12")
        mavenBom("io.netty:netty-bom:4.1.100.Final")

    }
}

dependencies {
    implementation("com.linecorp.armeria:armeria-thrift0.18")

    implementation(project(":spring-boot-thrift:protocol"))
}
