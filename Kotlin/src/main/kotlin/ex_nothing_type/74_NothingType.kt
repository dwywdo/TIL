package ex_nothing_type

fun throwing(): Nothing = throw Exception() // throw exception statement is also expression

fun main(args: Array<String>): Unit {
    println("start")
    var i: Int = throwing()
    println(i)
}

/**
 * Nothing type is for throwing exception but also treating as expression
 */
fun validate(num: Int) {
    val result: Int =
        if (num >= 0) num
        else throw Exception("num is negative") // Should be integer expression, but nothing is also possible!
}
