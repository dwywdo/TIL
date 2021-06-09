package ex_property_getter_setter

/**
 * Property : Field + Getter + Setter
 */
class Person {
    var age: Int = 0
        get() {
            return field // field means actual value of property inside the class
        }
        set(value) { // value is a value to store into property (field)
            field = if (value >= 0) value else 0
        }

    /**
     * For val property, only getter can exist
     */
    val isYoung get() = age < 30 // type of isYoung can be inferred from getter's return type.
}

fun main(args: Array<String>): Unit {
    val person = Person()
    person.age = -30
    println(person.age)
}
