fun main(args: Array<String>): Unit {
    val a: Byte = 125
    val b: Short = (100 + 200) * 100
    /**
     * In numeric literal value, you can put underscore(_) to make it to be easily read
     * Kotlin doesn't support octa expression for Int
     */
    var c: Int = 12_4354_6538 // 1243546538
    println(c)
    c = 0xFF_88_88
    c = 0b01010010_01100011_01110101_01000101
    var d: Long = -543_7847_3984_7238_4723

    c = a + b
    d = c + 10L
    var e: Float = 67.6f
    val f: Double = 658.456
    e = (e+f).toFloat()
    println(e)
}
