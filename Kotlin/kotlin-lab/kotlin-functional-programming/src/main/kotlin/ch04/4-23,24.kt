package ch04

/**
 * 코틀린에서는 기본 함수로 커링을 제공하지 않는다. 커링을 일반화해서
 * 코틀린에서도 쉽게 커링 함수를 만들 수 있게 해보자.
 */

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = {
    p1: P1 -> {
        p2: P2 -> {
            p3: P3 -> this(p1, p2, p3)
        }
    }
}

fun <P1, P2, P3, R> ((P1) -> (P2) -> (P3) -> R).uncurried(): (P1, P2, P3) -> R
    = { p1: P1, p2: P2, p3: P3 -> this(p1)(p2)(p3) }

fun main(args: Array<String>) {
    val multiThree = { a: Int, b: Int, c: Int -> a * b * c }
    val curried = multiThree.curried()
    println(curried(1)(2)(3))
    val uncurried = curried.uncurried()
    println(uncurried(1, 2, 3))
}
