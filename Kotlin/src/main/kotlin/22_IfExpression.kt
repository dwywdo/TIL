fun main(args: Array<String>): Unit {
    /**
     * If can be an expression if it has both 'if' and 'else'
     * The type of return values from if and else should be same (Int)
     */
    val value: Int = if (10 > 5) {
        println("10 > 5")
        10
    } else {
        println("10 < 5")
        5
    }
    println(value)
}
