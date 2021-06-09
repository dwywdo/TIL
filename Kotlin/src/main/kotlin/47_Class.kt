/**
 * - To create same form of object, you can use class
 * - Without access modified, default access modified is public
 * class [class name] {
 *   [property 1]
 *   [property 2]
 *   ...
 * - In Java, you can declare 'public' class in a file that has exact same name of public class
 *   and only one class can exist in one file.
 * - In Kotlin, file's name can be different to class's name, and multiple public class can exist in one file.
 */
class Person {
    var name: String = ""
    var age: Int = 0
}
fun main(args: Array<String>): Unit {
    val person: Person
    person = Person()
    person.name = "홍길동"
    person.age = 36

    val person2: Person = Person() // If you declare class, a special function with same name of class (constructor)
    person2.name = "김미영"
    person2.age = 29

    val person3: Person = Person()
    person3.name = "John"
    person3.age = 52
}
