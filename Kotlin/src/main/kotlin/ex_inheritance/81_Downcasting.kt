package ex_inheritance

fun main(args: Array<String>): Unit {
    val person: Person = Student("K", 15, 20210103)
    val person2: Person = Person("J", 29)

    var person3: Student? = person as Student
    person3 = person2 as Student // ClassCastException
    person3 = person2 as? Student // It's ok because of as? -> if casting fails, returns null
}
