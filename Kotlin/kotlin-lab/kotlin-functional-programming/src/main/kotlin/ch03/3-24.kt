package ch03

fun main(args: Array<String>) {
    println(maximum(listOf(1, 2, 10, 5, 7, 3)))
    println(tailrecMaximum(listOf(1, 2, 10, 5, 7, 3)))
}

tailrec fun tailrecMaximum(items: List<Int>, acct: Int = Int.MIN_VALUE): Int = when {
    items.isEmpty() -> error("empty list")
    items.size == 1 -> {
        println("ch03.ch03.head: ${items[0]}, maxValue: $acct")
        acct
    }
    else -> {
        val head = items.head()
        val maxValue = if (head > acct) head else acct
        println("ch03.ch03.head: $head, maxVal: $maxValue")
        tailrecMaximum(items.tail(), maxValue)
    }
}
