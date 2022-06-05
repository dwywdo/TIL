package ch04

fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean)
: PartialFunction<P, R> = PartialFunction(definedAt, this)

val condition2: (Int) -> Boolean = { it.rem(2) == 0 }
val body2: (Int) -> String = { "$it is even" }

val isEven2 = body2.toPartialFunction(condition2)

fun main(args: Array<String>) {
    if (isEven2.isDefinedAt(100)) {
        println(isEven2(100))
    } else {
        println("isDefinedAt(x) return false")
    }
}
