package ch04

/**
 * 부분 함수?
 * 일반적으로 허용되지 않는 입력값으로 함수를 호출하면 예외를 던지거나, 비정상임을 나타내는 값을 반환한다.
 * 하스켈 등의 함수형 언어에서는 Maybe / Either를 반환한다.
 * C나 Java와 같은 명령형 언어에서는 null / 음수 등을 반환한다.
 * 어떤 함수의 입력이, 특정한 값이나 범위 내에 있을 때만 정상적으로 함수를 동작시키려면?
 * 함수형 언어에서는 부분 함수를 이용해서 이 문제를 해결한다.
 * 부분 함수? 모든 가능한 입력 중, 일부 입력에 대해 결과만 정의한 함수.
 */
fun twice(x: Int) = x * 2
fun partialTwice(x: Int): Int =
    if (x < 100) {
        x * 2
    } else {
        throw IllegalArgumentException()
    }
