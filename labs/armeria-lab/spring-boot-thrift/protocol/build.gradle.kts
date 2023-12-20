import com.linecorp.thrift.plugin.CompileThrift

plugins {
    id("com.linecorp.thrift-gradle-plugin") version "0.5.0"
    id("java-library")
}

dependencies {
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    api("org.apache.thrift:libthrift:0.9.3-1")
}

tasks.withType<CompileThrift>().all {
    isRecurse = true
    thriftExecutable = "src/resources/thrift.osx-aarch_64"
}
