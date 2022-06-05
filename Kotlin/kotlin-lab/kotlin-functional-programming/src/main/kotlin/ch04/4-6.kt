package ch04
fun main(args: Array<String>) {
    val over10Values: ArrayList<Int> = ArrayList()
    val ints = IntArray(10) { i -> i * i}
    for (element in ints) {
        val twiceInt = element * 2
        if (twiceInt > 10) {
            over10Values.add(twiceInt)
        }
    }
    println(over10Values)
}

