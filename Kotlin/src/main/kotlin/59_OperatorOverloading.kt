/**
 * In Kotlin, operator overloading is possible using keyword 'operator' in member function of class
 * There's reserved function name for operator overloading
 * +a -> unaryPlus
 * -a -> unaryMinus
 * !a -> not
 * a+b -> plus
 * a-b -> minus
 * a*b -> times
 * a/b -> div
 * a%b -> rem
 * a+=b -> plusAssign
 * a-=b -> minusAssign
 * a*=b -> timesAssign
 * a/=b -> divAssign
 * a%=b -> remAssign
 * Following four operators have 'Int' type for function's return value.
 * a>b -> compareTo
 * a<b -> compareTo
 * a>=b -> compareTo
 * a<=b -> compareTo
 * Following two operators have 'Any?' type for functions' parameter and 'Boolean' type for its return value.
 * a==b -> equals -> a?.equals(b) ?: (b === null)
 * a!=b -> equals -> !(a?.equals(b) ?: (b === null))
 */
class Point(var x: Int = 0, var y: Int = 0) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun times(number:Int): Point {
        return Point(x * number, y * number)
    }

    operator fun div(number:Int): Point {
        return Point(x / number, y / number)
    }

    fun print() {
        println("x: $x, y: $y")
    }
}

fun main(args: Array<String>): Unit {
    val pt1 = Point(3, 7)
    val pt2 = Point(2, -6)

    val pt3 = pt1 + pt2
    val pt4 = pt3 * 6
    val pt5 = pt4 / 3

    pt3.print()
    pt4.print()
    pt5.print()
}
