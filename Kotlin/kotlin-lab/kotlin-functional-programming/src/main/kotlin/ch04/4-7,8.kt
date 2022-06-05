package ch04

fun main(args: Array<String>) {
    val ints: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result = ints
        .map { value -> value * 2 }
        .filter { value -> value > 10 }
    println(result)

    val result2 = ints
        .map { it * 2 }
        .filter { it > 10 }
    println(result)
}
