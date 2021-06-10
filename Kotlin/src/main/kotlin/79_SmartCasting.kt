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
     *
     */
    println(any.toString())
}
