package ch04

import java.lang.Math.abs

/**
 * compose 함수를 사용하지 않고, 함수를 매개변수로 넣어서 호출하는 예
 * 입력 숫자들의 리스트를 모두 음수로 만든 뒤, 최솟값을 구하는 함수
 */
fun main(args: Array<String>) {
    val absolute = { i: List<Int> -> i.map { abs(it) } }
    val negative = { i: List<Int> -> i.map { -it }}
    val minimum = { i: List<Int> -> i.minOf { it } }

    val result = minimum(negative(absolute(listOf(3, -1, 5, -2, -4, 8, 14))))
    println(result)

    /**
     * 함수 합성을 이용해 매개변수나 타입 선언 없이 함수를 만드는 방식?
     * = 포인트 프리 스타일
     * 지나치게 사용하면 가독성을 해치기도 하지만, 일반적으로 가독성을 높이고 간결하게 해준다.
     */
    val composed = minimum compose negative compose absolute
    val result2 = composed(listOf(3, -1, 5, -2, -4, 8, 14))
    println(result2)
}

