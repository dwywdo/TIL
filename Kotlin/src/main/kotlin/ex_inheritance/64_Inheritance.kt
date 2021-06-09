package ex_inheritance

/**
 * To make it class available for inheritance, class should be declared with 'open' keyword.
 * It's because in Kotlin, all class declaration is 'final' by default.
 */
open class Person(val name: String, val age: Int)

/**
 * : Person(name, age) means calling constructor of Person class
 * When you make class inherits from superclass, the constructor of super class must be called.
 * When you do inheritance, superclass's properties and member function is copied to subclass
 */
class Student(name: String, age: Int, val id: Int) : Person(name, age)
// name and age of Person(name, age) are from Student(name: String, age:Int, ...)

fun main(args: Array<String>): Unit {
    val person = Person("홍길동", 35)
    val student = Student("김길동", 23, 20171217)

    /**
     * Upcasting: You can refer subclass's instance by reference variable with superclass type.
     * However, you can not refer it by reference variable with subclass type again
     */
    val person2: Person = Student("John", 31, 19910225)

    // val student2: Student = person2
    // Error: Type mismatch: inferred type is Person but Student was expected
}
