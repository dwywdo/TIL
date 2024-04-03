import com.linecorp.thrift.plugin.CompileThrift

plugins {
    id("com.google.osdetector") version "1.7.3"
    id("com.linecorp.thrift-gradle-plugin") version "0.6.1"
    id("java-library")
}

dependencies {
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    api("org.apache.thrift:libthrift:0.9.3-1")
}

tasks.withType<CompileThrift>().all {
    recurse = true
    thriftExecutable = "src/resources/thrift.${osdetector.classifier}"
}
