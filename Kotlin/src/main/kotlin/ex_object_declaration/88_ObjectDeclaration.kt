package ex_object_declaration

/**
 * Object declaration like it's a class creates a singletone instance through a whole program
 */
object Person {
    var name: String = ""
    var age: Int = 0
    fun print() {
        println(name)
        println(age)
    }
}

fun main(args: Array<String>): Unit {
    Person.name = "Singletone"
    Person.age = 45
    Person.print()
}
