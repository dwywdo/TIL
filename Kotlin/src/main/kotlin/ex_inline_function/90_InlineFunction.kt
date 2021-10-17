package ex_inline_function

/**
 * If inline keyword is used, when compiling the code itself turns into the logic inside inline function
 * Inline function can't be called recursively.
 */
inline fun hello() {
    println("Hello")
    println("Kotlin")
}

fun main(args: Array<String>): Unit {
    hello()
    hello()
    hello()
    /**
     * After compilation
     * println("Hello")
     * println("Kotlin")
     * println("Hello")
     * println("Kotlin")
     * println("Hello")
     * println("Kotlin")
     */
}
