package ch03

fun main(args: Array<String>) {
    println(take(5, listOf(1, 2, 3, 4, 5, 6, 7)))
    println(tailrecTake(5, listOf(1, 2, 3, 4, 5, 6, 7)))
}

tailrec fun tailrecTake(n: Int, list: List<Int>, acc: List<Int> = listOf()): List<Int> = when {
    0 >= n -> acc
    list.isEmpty() -> acc
    else -> {
        val takeList = acc + listOf(list.head())
        tailrecTake(n - 1, list.tail(), takeList)
    }
}
