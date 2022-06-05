package ch03

fun main(args: Array<String>) {
    printFiboRecursionCount(6)
}

fun printFiboRecursionCount(n: Int): Int {
    println("ch03.fiboRecursion($n)")
    return when (n) {
        0 -> 0
        1 -> 1
        else -> printFiboRecursionCount(n - 2) + printFiboRecursionCount(n - 1)
    }
}
