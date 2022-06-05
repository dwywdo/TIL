package ch03

fun main(args: Array<String>) {
    println(even(9999))
    println(odd(9999))
    println(even(999999))
}

fun even(n: Int): Boolean = when (n) {
    0 -> true
    else -> odd(n - 1)
}

fun odd(n: Int): Boolean = when (n) {
    0 -> false
    else -> even(n - 1)
}
