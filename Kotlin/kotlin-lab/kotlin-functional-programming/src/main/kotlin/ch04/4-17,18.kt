package ch04

fun <P1, P2, R> ((P1, P2) -> R).partial1(p1: P1): (P2) -> R {
    return { p2 -> this(p1, p2) }
}

fun <P1, P2, R> ((P1, P2) -> R).partial2(p2: P2): (P1) -> R {
    return { p1 -> this(p1, p2) }
}

fun main(args: Array<String>) {
    val func = { a: String, b: String -> a + b }

    /**
     * partialAppliedFunc1은 값으로 평가되지 않고, 남은 매개변수를 받아서
     * 결과를 반환하는 함수의 참조만 가지고 있다.
     * 함수에 어떤 값을 적용했다? = 어떤 값을 함수의 매개변수로 넣었다.
     * != 실제로 호출이 일어나서 결과를 받는다는 의미가 아니다.
     */
    val partialAppliedFunc1 = func.partial1("Hello")
    val result1 = partialAppliedFunc1("World")
    println(result1)

    val partialAppliedFunc2 = func.partial2("World")
    val result2 = partialAppliedFunc2("World")
    println(result2)
}
