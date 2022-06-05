package ch03

fun main(args: Array<String>) {
    // val initialValue = ch03.repeat(5)
    // initialValue.ch03.take(3).forEach { print("$it") }
    println(takeSequence(5, repeat(3)))
}

// fun ch03.repeat(n: Int): Sequence<Int> = sequenceOf(n) + ch03.repeat(n)
// -> Stack overflow: 코틀린은 기본적으로 + 연산자가 lazy하지 않기 때문에..!

fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }
