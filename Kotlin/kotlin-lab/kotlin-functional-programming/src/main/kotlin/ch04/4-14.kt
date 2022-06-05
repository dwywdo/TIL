package ch04

val isEven = PartialFunction<Int, String>(
    { it % 2 == 0 }, { "$it is even" }
)

fun main(args: Array<String>) {
    if (isEven.isDefinedAt(100)) {
        print(isEven(100))
    } else {
        print("isDefinedAt(x) return false")
    }
}
