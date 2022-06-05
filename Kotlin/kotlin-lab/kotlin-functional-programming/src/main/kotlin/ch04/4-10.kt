package ch04

/**
 * 모든 입력값에 대해 결과를 정의했으므로 부분 함수가 아니다.
 */
fun sayNumber1(x: Int): String = when (x) {
    1 -> "One!"
    2 -> "Two!"
    3 -> "Three!"
    else -> "Not between 1 and 3"
}
