package ex_const

/**
 * Like inline function, `const` after `val` replaces the code accesing the `val` with value itself
 * const keyword can be used for making some part of Kotlin code into `literal`
 */
const val hello = "Hello" + " World!"

object Foo {
    const val bar = "bar"
}

fun main(args: Array<String>): Unit {
    println(hello)
    println(Foo.bar)
    println(hello)
    println(Foo.bar)

    /**
     * println("Hello World!")
     * println("bar")
     * println("Hello World!")
     * println("bar")
     */
}
