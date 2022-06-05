package ch03

fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)

fun main(args: Array<String>) {
    println(maximum(listOf(1,3,2,8,4)))
}

fun maximum(items: List<Int>): Int = when {
    items.isEmpty() -> error("empty list")
    1 == items.size -> {
        println("ch03.ch03.head: ${items[0]}, maxVal: ${items[0]}")
        items[0]
    }
    else -> {
        val head = items.head()
        val tail = items.tail()
        val maxVal = maximum(tail)
        println("ch03.ch03.head: $head, maxVal: $maxVal")
        if (head > maxVal) head else maxVal
    }
}

