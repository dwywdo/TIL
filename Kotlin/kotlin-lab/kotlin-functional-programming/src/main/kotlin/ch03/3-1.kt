package ch03

fun main(args: Array<String>) {
    println(fiboDynamic(10, IntArray(100)))
}

/**
 * 피보나치 수열을 명령형으로 구현한 예제 (동적계획법)
 */
fun fiboDynamic(n: Int, fibo: IntArray): Int {
    fibo[0] = 0
    fibo[1] = 1
    for (i in 2..n) {
        fibo[i] = fibo[i-1] + fibo[i-2]
    }
    return fibo[n]
}
