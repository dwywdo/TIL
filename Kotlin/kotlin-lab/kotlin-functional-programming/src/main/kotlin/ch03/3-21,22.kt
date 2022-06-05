package ch03

import kotlin.math.cos

fun main(args: Array<String>) {
    println(findFixPoint(1.0))
}

tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (x == cos(x)) x else findFixPoint(cos(x))

fun optimizedFindFixPoint(): Double {
    var x = 1.0
    while (true) {
        val y = cos(x)
        if (x == y) return x
        x = y
    }
}
