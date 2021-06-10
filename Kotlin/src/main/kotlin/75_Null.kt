import ex_inheritance.Person

/**
 * ? after type makes variable nullable
 * ? after Byte, Short, Int, Long, Float, Double, Char, Boolean makes variable 'reference variable',
 * which means data is allocated to heap area, not stack area
 * In Kotlin, without ?, null can not be assigned to variable
 */
fun main(args: Array<String>): Unit {
    var person: Person? = ex_inheritance.Person("K", 30)
    person = null

    var num: Int? = null
    num = 10

    // type of 'whatIsType' is String? (null is Nothing? type and "Test" is String type so Nothing? + String = String?
    var whatIsType = if (true) "Test" else null
}
