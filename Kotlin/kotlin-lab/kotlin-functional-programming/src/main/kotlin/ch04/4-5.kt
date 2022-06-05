package ch04

fun main(args: Array<String>) {
    val twiceSum: (Int, Int) -> Int = { x, y -> (x + y) * 2 }
    println(higherOrder(twiceSum, 8, 2))
}
