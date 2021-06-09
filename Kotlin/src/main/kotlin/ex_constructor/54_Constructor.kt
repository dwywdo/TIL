package ex_constructor

class Person constructor(name: String, age: Int) {
    /**
     * You can use a parameter in constructor for declaration & initialization
     */
    val name: String = name
    val age: Int

    /**
     * Parameters in constructor can be used in 'init' block
     * By using 'init' block, you don't need to declare and initialize properties at the same time.
     */
    init {
        this.age = age
    }

    /**
     * You can use multiple init block in a class.
     */
    val description: String

    init {
        this.description = "이름은 $name, 나이는 $age"
    }
}
fun main(args: Array<String>): Unit {
    val person = Person("홍길동", 46)
    println(person.name)
    println(person.age)
    println(person.description)
}
