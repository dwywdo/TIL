fun main(args: Array<String>): Unit {
    /**
     * There's no type for object so omit person's type (val person: ? = object { ... })
     * Actually, there's implicit type defined by compiler
     * name, age are called 'property' and they should be declared and initialized at once
     * Property in Kotlin != Field in JAVA
     * Property == Field + Getter + Setter
     */
    val person = object {
        val name: String = "홍길동"
        val age: Int = 36
    }
    val person2 = object {
        val name: String = "홍길동"
        val age: Int = 36
    }
    /**
     * person & person2's types are actually different.
     */
    println(person.name)
    println(person.age)

}
