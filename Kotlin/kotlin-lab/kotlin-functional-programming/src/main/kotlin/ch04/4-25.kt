package ch04

/**
 * 합성 함수에 대해
 * 합성 함수? = 함수를 매개변수로 받고, 함수를 반환할 수 있는 고차 함수를 이용해서 두 개의 함수를 결합하는 것.
 */

fun main(args: Array<String>) {
    println(composed(3))
}

fun composed(i: Int) = addThree(twice(i))

fun addThree(i: Int) = i + 3
