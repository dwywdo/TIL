package ch03

fun main(args: Array<String>) {
    println(zip(listOf(1, 3, 5), listOf(2, 4)))
}

fun zip(list1: List<Int>, list2: List<Int>) : List<Pair<Int, Int>> = when {
    list1.isEmpty() || list2.isEmpty() -> listOf()
    else -> listOf(Pair(list1.head(), list2.head())) + zip(list1.tail(), list2.tail())
}
