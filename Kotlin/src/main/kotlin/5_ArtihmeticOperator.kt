fun main(args: Array<String>): Unit {
    /**
     * If type of num is set to Double like below,
     * val num: Double = 15 - 4 * 3
     * -> Error: Type mismatch: inferred type is Int but Double was expected
     */
    val num: Int = 15 - 4 * 3
    val num2: Int = 65 % 7
    val num3: Double = 7.5 / 5 + 22.25
    val num4: Double = num / num2 + 0.7

    println(num) // 3
    println(num2) // 2
    println(num3) // 23.75
    /**
     * 3 / 2 -> 1 + 0.7 = 1.7
     */
    println(num4) // 1.7(?)
    /**
     * (Double)3 / 2 = 1.5 + 0.7 = 2.2
     * Why use .toDouble(), not (Double) like JAVA?
     * It's because Int and Double is not in inheritance relationship. (Note that there's no pritimitve type in Kotlin)
     */
    val num5: Double = num.toDouble() / num2 + 0.7 // Note that type of 'num' is still Int.
    println(num5)
}
