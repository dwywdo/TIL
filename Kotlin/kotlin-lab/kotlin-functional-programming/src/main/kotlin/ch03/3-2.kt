package ch03

fun main(args: Array<String>) {
    println(fiboRecursion(150))
}

/**
 * 재귀로 구현한 피보나치 수열 - 고정 메모리 할당이나 값의 변경이 없어서
 * 메모리 할당 없이 스택을 활용한다.
 * ch03.fiboRecursion(150)으로 실행하면? 실행시간이 너무 오래 걸린다.
 * - 동적 계획법에 비해 성능이 느리다
 * - 스택 오버플로우 에러가 발생할 수 있다.
 *
 */
fun fiboRecursion(n: Int): Int = when (n){
    0 -> 0
    1 -> 1
    else -> fiboRecursion(n - 1) + fiboRecursion(n - 2)
}
