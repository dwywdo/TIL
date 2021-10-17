tasks.register("hello") {
    doLast {
        println("hello")
    }
}

// Register "copy" task as Copy task type.
tasks.register<Copy>("copy") {
    from(file("srcDir"))
    into(buildDir)
    println("Copy for task copy is done")
}

val helloTask by tasks.registering {
    doLast {
        println("helloTask")
    }
}

val copyTask by tasks.registering(Copy::class) {
    from(file("srcDir"))
    into(buildDir)
    println("Copy for task copyTask is done")
}

tasks.withType<Tar>().configureEach {
    enabled = false
}

tasks.register("test") {
    dependsOn(tasks.withType<Copy>())
}

println(tasks.named("helloTask").get().name)
println(tasks.named<Copy>("copy").get().destinationDir)

abstract class CustomTask @Inject constructor(
    private val meesage: String,
    private val number: Int    
): DefaultTask()

tasks.register<CustomTask>("myTask", "hello", 42)
