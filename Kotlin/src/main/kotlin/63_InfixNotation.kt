class InfixPoint(var x: Int = 0, var y: Int = 0) {
    infix fun from(base: InfixPoint): InfixPoint {
        return InfixPoint(x - base.x, y - base.y)
    }

    fun print() {
        println("x: $x, y: $y")
    }
}

fun main(args: Array<String>): Unit {
    val pt = InfixPoint(3, 6) from InfixPoint(1, 1)
    pt.print()
}
