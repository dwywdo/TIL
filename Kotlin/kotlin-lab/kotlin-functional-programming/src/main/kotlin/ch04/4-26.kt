package ch04

/**
 * 하스켈에서는 합성 함수를 표현하기 위한 연산자 .가 있다
 * addThree . twice
 * 코틀린에는 없으므로 합성 함수를 간결하게 생성하기 위한 확장함수를 만들어보자.
 */
fun main(args: Array<String>) {
    val addThree = { i: Int -> i + 3 }
    val twice = { i: Int -> i * 2 }
    val composedFunc = addThree compose twice
    println(composedFunc(3))
}

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput))}
}
