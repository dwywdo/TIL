package ex_any_class

/**
 * All classes automatically inherits from 'Any' class
 * Any class has 3 member functions
 * open class Any {
 *      open operator fun equals(other: Any?) : Boolean
 *      -> operator overriding for ==
 *      open fun hashCode(): Int
 *      -> returns object's unique identifier
 *      open fun toString(): String
 *      -> returns String that represents object's content
 * }
 */

class Building(val name: String = "", val date: String = "", val area: Int = 0) {
    override fun toString(): String =
        "${this.name}\n" + "${this.date}\n" + "${this.area}m^2"
}

fun main(args: Array<String>): Unit {
    val building = Building("Kotlin", area= 100)
    printObject(building)
}

fun printObject(any: Any) {
    println(any.toString())
    println(any)
}
