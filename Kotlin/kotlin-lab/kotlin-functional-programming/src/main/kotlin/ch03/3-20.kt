package ch03

fun main(args: Array<String>) {
    println(fiboFP(6))
}

fun fiboFP(n: Int): Int = fiboFP(n, 0, 1)

tailrec fun fiboFP(n: Int, first: Int, second: Int): Int = when (n) {
    0 -> first
    1 -> second
    else -> fiboFP(n - 1, second, first + second)
}

