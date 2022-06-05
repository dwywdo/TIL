package ch03

tailrec fun tailrecZip(list1: List<Int>, list2: List<Int>, acc: List<Pair<Int, Int>> = listOf()): List<Pair<Int, Int>> = when {
    list1.isEmpty() || list2.isEmpty() -> acc
    else -> {
        val zipList = acc + listOf(Pair(list1.head(), list2.head()))
        tailrecZip(list1.tail(), list2.tail(), zipList)
    }
}
