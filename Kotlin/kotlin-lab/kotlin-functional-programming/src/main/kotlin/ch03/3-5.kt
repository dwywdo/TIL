package ch03

fun main(args: Array<String>) {
    println(sum(5))
}

fun sum(n: Int): Int = when {
    n < 0 -> 0
    else -> n + sum(n - 1)
}
