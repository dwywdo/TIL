package ch04

fun main(args: Array<String>) {
    val calcTwiceSum = TwiceSum()
    println(calcTwiceSum.calc(8, 2))
}

class TwiceSum: Calcable {
    override fun calc(x: Int, y: Int): Int {
       return (x + y) * 2
    }
}
