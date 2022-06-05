package ch04

fun multiThree(a: Int, b: Int, c: Int): Int = a * b * c
/**
 * 위의 함수를 부분 적용 함수를 사용해서 체인으로 만들어보자
 *
 * 하나의 매개변수 a만 받아서 Int -> ( Int -> Int ) 형태의 부분 적용 함수 반환
 * 부분 적용 함수에서는 두번째 매개변수 b를 받아서 Int -> Int 형태의 부분 적용 함수 반환
 * 따라서, 매개변수가 한 개인 부분 적용 함수의 체인이므로 커링 함수이다.
 */
fun curryingMultiThree(a: Int) = { b: Int -> { c: Int -> a * b * c} }

fun main(args: Array<String>) {
    println(multiThree(1, 2, 3))

    val partial1 = curryingMultiThree(1)
    val partial2 = partial1(2)
    val partial3 = partial2(3)

    println(partial3)
    println(curryingMultiThree(1)(2)(3))
}


