package ex_companion_object

/**
 * Companion object is anonymous object included in a class
 * It's used for being shared by all instances of a class
 * It's similar to `static` keyword for JAVA. There's no `static` keyword in Kotlin
 */
class Person private constructor() {
    companion object {
        fun create(): Person{
            countCreated += 1
            return Person()
        }

        /**
         * Access modifier for setter is private so that it could be set only in a class
         */
        var countCreated = 0
            private set
    }
}

fun main(args: Array<String>) {
    val a = Person.create()
    val b = Person.create()
    println(Person.countCreated)
    println(Person.Companion.countCreated)
}
