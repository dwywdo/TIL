package ch04

val condition: (Int) -> Boolean = { it in 1..3 }
val body: (Int) -> String = {
    when (it) {
        1 -> "One!"
        2 -> "Two!"
        3 -> "Three!"
        else -> throw IllegalArgumentException()
    }
}
val oneTwoThree = PartialFunction(condition, body)

fun main(args: Array<String>) {
    if (oneTwoThree.isDefinedAt(3)) {
        println(oneTwoThree(3))
    } else {
        print("isDefinedAt(x) return false")
    }
}

