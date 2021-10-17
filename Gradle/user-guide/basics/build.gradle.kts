import org.apache.commons.codec.binary.Base64

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        "classpath"(group = "commons-codec", name = "commons-codec", version = "1.2")
    }
}

defaultTasks("clean", "run")

task("clean") {
    doLast {
        println("Default Cleaning!")
    }
}

tasks.register("run") {
    doLast {
        println("Default Running!")
    }
}

tasks.register("other") {
    doLast {
        println("I'm not a default tasks!")
    }
}


tasks.register("hello") {
    doLast {
        println("Hello World!")
    }
}

tasks.register("upper") {
    doLast {
        val someString = "Euiyub Jung"
        println("Original: $someString")
        println("Upper case: ${someString.toUpperCase()}")
    }
}

tasks.register("count") {
    doLast {
        repeat(4) { print("$it ") }
    }
}

tasks.register("intro") {
    dependsOn("hello")
    doLast {
        println("I'm Gradle")
    }
}

tasks.register("taskX") {
    dependsOn("taskY")
    doLast {
        println("taskX")
    }
}

tasks.register("taskY") {
    doLast {
        println("taskY")
    }
}

repeat(4) { counter ->
    tasks.register("task$counter") {
        doLast {
            println("I'm task number $counter")
        }
    }
}

// Add runtime dependency on tasks
tasks.named("task0") { dependsOn("task2", "task3") }

// Add behavior to an existing task
tasks.named("hello") {
    doLast {
        println("Hello Earth")
    }
}

tasks.named("hello") {
    doLast {
        println("Hello Sun")
    }
}

// Using Ant Tasks
tasks.register("loadfile") {
    doLast {
        val files = file("./antLoadfileResources").listFiles().sorted()
        files.forEach { file ->
            if (file.isFile) {
                ant.withGroovyBuilder {
                    "loadfile"("srcFile" to file, "property" to file.name)
                }
                println(" *** ${file.name} ***")
                println("${ant.properties[file.name]}")
            }
        }
    }
}

tasks.register("encode") {
    doLast {
        val encodedString = Base64().encode("hello world\n".toByteArray())
        println(String(encodedString))
    }
}
