package ch04

/**
 * 입력값 1, 2, 3에 대한 결과만 정의하였다. 이 외의 값은 예외를 발생시킨다. 따라서 부분함수다.
 */
fun sayNumber2(x: Int): String = when (x) {
    1 -> "One!"
    2 -> "Two!"
    3 -> "Three!"
    else -> throw IllegalArgumentException()
}
