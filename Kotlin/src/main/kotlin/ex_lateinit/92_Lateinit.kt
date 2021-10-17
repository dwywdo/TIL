package ex_lateinit


class Point(val x: Int, val y: Int)

/**
 * If you don't wanna declare and initialize property of class at the same time
 * You could just initialize with 0, "" for primitive types, but if it's user declared class, it's kinda annoying
 * You can resolve this problem with `lateinit` keyword
 *
 * If you'd like to know whether a var is initialized or not, you can check with rect::pt.isInitialized
 */
class Rect {
    lateinit var pt: Point
    lateinit var pt2: Point

    val width: Int get() = pt2.x - pt.x
    val height: Int get() = pt2.y - pt.y
    val area get() = width * height
}

fun main(args: Array<String>) {
    val rect = Rect()
    /**
     * if (rect::pt.isInitialized) {
     *   ...
     * }
     */

    rect.pt = Point(3, 3)
    rect.pt2 = Point(6, 5)

    println("너비: ${rect.width}")
    println("높이: ${rect.height}")
    println("넓이: ${rect.area}")
}
