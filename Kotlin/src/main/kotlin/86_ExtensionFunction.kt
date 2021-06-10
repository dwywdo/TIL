/**
 * If extension function's name is same as member function already defined in a class,
 * the extension function is not called
 */
fun String.isNumber(): Boolean {
    var i = 0
    while (i < this.length) {
        if (!('0' <= this[i] && this[i] <= '9'))
            return false
        i += 1
    }
    return true
}

fun main(args: Array<String>): Unit {
    println("1234567890".isNumber())
    println("500원".isNumber())
}
