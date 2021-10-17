fun main(args: Array<String>): Unit {
    val number: Int? = null
    val number2 = 1225

    checkNull(number)
    checkNull(number2)
}

fun checkNull(any: Any?) {
    if (any == null) {
        println("null came")
        return
    }

    /** Here,
     * you can ensure that any is not null
     * Like this, when you can be 100% sure that variable is not null, variable becomes Not-null type
     * That's why we can call like any.toString(), not any?.toString()
     * 특정 조건을 만족하는 경우, 위에서의 널 검사처럼.. 변수의 타입을 완벽히 추록해낼 수 있을때 알아서 스마트캐스팅을 하기에, any.toString()으로 호출이 가능한것이다.
     */
    println(any.toString())
}
