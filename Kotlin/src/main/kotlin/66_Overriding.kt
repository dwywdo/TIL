/**
 * To override function from subclass, declare function with 'open' keyword as well in superclass.
 */
open class A {
    open fun func() = println("A")
}

/**
 * 'override' keyword includes 'open' keyword implicitly, which means override member function can be overrided again.
 * If you don't want this implication, you can use 'final' keyword
 */
class B: A() {
    override fun func() {
        super.func()
        println("B")
    }
    /** To Prohibit overriding again in subclass of B,
     *
     * final override fun func() {
     *      super.func()
     *      println("B")
     * }
     */
}

fun main(args: Array<String>): Unit {
    A().func()
    B().func()
}
