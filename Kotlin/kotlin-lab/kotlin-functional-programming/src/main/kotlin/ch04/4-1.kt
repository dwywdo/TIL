package ch04

fun higherOrderFunction1(func: () -> Unit): Unit {
    func()
}

fun higherOrderFunction2(): () -> Unit {
    return { println("Hello, Kotlin") }
}
