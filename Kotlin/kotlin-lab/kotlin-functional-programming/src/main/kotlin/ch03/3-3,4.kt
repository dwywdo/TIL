package ch03

fun main(args: Array<String>) {
    helloFuncRecursive(5)
}

/* 아래는 종료 조건이 없어 에러가 발생
fun ch03.helloFuncRecursive() {
    println("Hello")
    ch03.helloFuncRecursive()
}
*/

fun helloFuncRecursive(n: Int) {
    when {
        n <= 0 -> return
        else -> {
            println("Hello")
            helloFuncRecursive(n - 1)
        }
    }
}
