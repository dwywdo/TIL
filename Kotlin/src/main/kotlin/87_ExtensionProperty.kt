/**
 * There's no field in extension property so we can't use 'field' identifier
 */

val String.isLarge: Boolean
    get() = this.length >= 10

fun main(args: Array<String>): Unit {
    println("1234567890".isLarge)
    println("500원".isLarge)
}
