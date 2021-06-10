package ex_inheritance

/**
 * In Kotlin, 'is' operator is same as 'instanceof' in Java
 */
class Professor(name: String, age: Int): Person(name, age)

fun main(args: Array<String>): Unit {
    val person: Person = Student("Mark Zuckerberg", 33, 20171225)
    println("${person is Person}")
    println("${person is Student}")
    println("${person is Professor}")

    val person2: Person = Professor("Kim", 48)
    println("${person2 is Person}")
    println("${person2 is Student}")
    println("${person2 is Professor}")
}

/**
 * 'is' operator also can be used in 'when' clause
 *  val person: Person
 *  when (person) {
 *  is Person -> {}
 *  is Student -> {}
 *  is Professor -> {}
 */
