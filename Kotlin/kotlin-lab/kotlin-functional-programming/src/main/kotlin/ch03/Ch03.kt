package ch03

import java.math.BigDecimal
import kotlin.math.sqrt

fun main(args: Array<String>) {

    //println(ch03.power(3.0,5)) // 3-2
    //println(ch03.fact(5)) // 3-3
    //println(ch03.toBinary(12)) // 3-4
    println(tailrecToBinary(12))
    //println(ch03.replicate(3, 5)) // 3-5
    //println(ch03.elem(3, listOf(1,2,4,5,3))) // 3-6
    println(tailrecElem(3, listOf(1,2,4,5,3))) // 3-14
    //println(ch03.quickSort(listOf(1, 9, 3, 15, 3, 8, 10))) // 3-8
    //println(ch03.gcd(10, 15)) // 3-9
    //println(ch03.factMemoization(5))
    //println(ch03.factFP(5))

    /**
     * ch03.fiboFP(6) Call
     * ch03.fiboFP(6) -> ch03.fiboFP(6, 0, 1)
     * ch03.fiboFP(6, 0, 1) -> ch03.fiboFP(5, 1, 0 + 1) = ch03.fiboFP(5, 1, 1)
     * ch03.fiboFP(5, 1, 1) -> ch03.fiboFP(4, 1, 1 + 1) = ch03.fiboFP(4, 1, 2)
     * ch03.fiboFP(4, 1, 2) -> ch03.fiboFP(3, 2, 1 + 2) = ch03.fiboFP(3, 2, 3)
     * ch03.fiboFP(3, 2, 3) -> ch03.fiboFP(2, 3, 2 + 3) = ch03.fiboFP(2, 3, 5)
     * ch03.fiboFP(2, 3, 5) -> ch03.fiboFP(1, 5, 3 + 5) = ch03.fiboFP(1, 5, 8)
     * ch03.fiboFP(1, 5, 8) -> ch03.fiboFP(0, 8, 5 + 8) = ch03.fiboFP(0, 8, 13) = 8
     */
    //println(ch03.fiboFP(6))
    println(replicate(3, 5))
    println(tailrecRepliacte(3, 5))

    println(trampoline(trampolineFact(BigDecimal(10))))
}

/**
 * 3-2. X의 n승을 구하는 함수를 재귀로 구현해보자.
 * fun ch03.power(x: Double, n: Int): Double
 */
fun power(x: Double, n: Int): Double = when {
    n <= 0 -> 1.0
    else -> x * power(x, n - 1)
}

/**
 * 3-3. 입력 n의 팩터리얼(Factorial)인 n!을 구하는 함수를 재귀로 구현해보자.
 */
fun fact(n: Int): Int = when {
    n <= 0 -> 1
    else -> {
        println("calc $n")
        n * fact(n-1)
    }
}

/**
 * 3-4. 10진수 숫자를 입력받아서 2진수 문자열로 변환하는 함수를 작성하라
 */
fun toBinary(n: Int): String = when {
    n <= 0 -> ""
    else -> toBinary(n / 2) + (n % 2).toString()
}

/**
 * 3-5. 숫자를 두 개 입력받은 후 두 번째 숫자를 첫 번째 숫자만큼 가지고 있는 리스트를 반환하는 함수를 재귀로 작성해보자.
 * ch03.replicate(3, 5)  = [5, 5, 5]
 * fun ch03.replicate(n: Int, element: Int): List<Int>
 */
fun replicate(n: Int, element: Int): List<Int> = when (n) {
    0 -> emptyList()
    else -> listOf(element) + replicate(n - 1, element)
}

/**
 * 3-6. 입력값 n이 리스트에 존재하는지 확인하는 함수를 재귀로 작성해보자.
 * fun ch03.elem(num: Int, list: List<Int>): Boolean
 */
fun elem(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    else -> (list.head() == num) or elem(num, list.tail())
}

/**
 * 3-7. 코드 3-9의 ch03.take 함수를 참고하여 ch03.repeat 함수를 테스트하기 위해서 사용한 ch03.takeSequence 함수를 작성해보자.
 * 그리고 ch03.repeat 함수가 잘 동작하는지 테스트해 보자.
 */
fun takeSequence(n: Int, sequence: Sequence<Int>) : List<Int> = when {
    n <= 0 -> listOf()
    sequence.none() -> listOf()
    else -> listOf(sequence.first()) + takeSequence(n-1, sequence.drop(1))
}

/**
 * 3-8 퀵 정렬 알고리즘의 quicksort 함수를 작성해보자.
 */
fun quickSort(list: List<Int>): List<Int> = when {
    list.size <= 1 -> list
    else -> {
        val pivot = list.first()
        val (less, greater) = list.partition { it < list.first() }
        quickSort(less) + listOf(pivot) + quickSort(greater.drop(1))
    }
}

/**
 * 3-9. 최대공약수를 구하는 ch03.gcd 함수를 작성해보자.
 * fun ch03.gcd(m: Int, n: Int): Int
 */
fun gcd(m: Int, n: Int): Int = when {
    m % n == 0 -> n
    else -> gcd(n, m % n)
}

/**
 * 3-10. 연습문제 3-3에서 작성한 factorial 함수를 메모이제이션을 사용해서 개선해보라.
 */
var factMemo = Array(100) { -1 }
fun factMemoization(n: Int): Int = when {
    n == 0 -> 1
    factMemo[n] != -1 -> factMemo[n]
    else -> {
        println("calc $n")
        factMemo[n] = n * factMemoization(n - 1)
        factMemo[n]
    }
}

/**
 * 3-11, 12. 연습문제 3-10에서 작성한 ch03.factMemoization 함수를 함수형 프로그래밍에 적합한 방식으로 개선해 보라.
 * 꼬리재귀이기도 하다..!
 */
tailrec fun factFP(n: Int, value: Int = 1): Int = when(n) {
    1 -> value
    else -> {
        println("calc $n")
        factFP(n - 1, n * value)
    }
}

/**
 * 3-13. 연습문제 3-2에서 작성한 ch03.power 함수가 꼬리 재귀인지 확인해보자. 만약 아니라면 개선해보자.
 */
tailrec fun powerFP(x: Double, n: Int, acct: Double): Double = when(n) {
    0 -> acct
    else -> powerFP(x, n - 1, acct * x)
}

/**
 * 3-14. 연습문제 3-4에서 작성한 ch03.toBinary 함수가 꼬리재귀인지 확인해보자. 만약 아니라면 개선해보자.
 */
tailrec fun tailrecToBinary(n: Int, acc: String = ""): String = when {
    n <= 0 -> acc
    else -> tailrecToBinary(n / 2, (n % 2).toString() + acc)
}

/**
 * 3-15. 연습문제 3-5에서 작성한 ch03.replicate 함수가 꼬리 재귀인지 확인해보자. 만약 아니라면 개선해보자.
 */
tailrec fun tailrecRepliacte(n: Int, element: Int, acc: List<Int> = listOf()): List<Int>
= when(n) {
    0 -> acc
    else -> tailrecRepliacte(n - 1, element, acc + listOf(element))
}

/**
 * 3-16. 연습문제 3-6에서 작성한 ch03.elem 함수가 꼬리 재귀인지 확인해보자. 만약 아니라면 개선해보자.
 */
tailrec fun tailrecElem(num: Int, list: List<Int>, accResult: Boolean = false): Boolean = when {
    list.isEmpty() -> accResult
    else -> {
        tailrecElem(num, list.tail(), accResult or (list.head() == num))
    }
}

/**
 * 3-17. 입력값 n의 제곱근을 2로 나눈 값이 1보다 작을 때까지 반복하고, 최초의 1보다 작은 값을 반환하는 함수를 상호 재귀로 구현하라.
 * 이 때 입력 값은 2보다 크다.
 */
fun getRoot(x: Double): Double = when {
    x < 1 -> x
    else -> divideByTwo(getRoot(x))
}

fun divideByTwo(x: Double): Double = when {
    x < 1 -> x
    else -> getRoot(x / 2)
}

/**
 * 3-18. 3-17을 tranmpoline으로 구현해보자
 */
fun tranmpolineGetRoot(x: Double): Bounce<Double> = when {
    x < 1 -> Done(x)
    else -> More { trampolineDivideByTwo(sqrt(x)) }
}

fun trampolineDivideByTwo(x: Double): Bounce<Double> = when {
    x < 1 -> Done(x)
    else -> More { tranmpolineGetRoot(x / 2) }
}

/**
 * 3-19. 연습문제 3-2의 factorial을 trampoline으로 구현해보자.
 */
private fun trampolineFact(n: BigDecimal, value: BigDecimal = BigDecimal.ONE): Bounce<BigDecimal> = when (n) {
    BigDecimal.ONE -> Done(value)
    else -> More { trampolineFact(n.dec(), n * value) }
}
