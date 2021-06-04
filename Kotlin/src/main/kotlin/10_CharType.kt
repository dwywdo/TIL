fun main(args: Array<String>): Unit {
    var ch: Char = 'A'
    println(ch)

    /**
     * Kotlin uses Unicode for character representation
     */
    ch = '\uAC00'
    println(ch)

    /**
     * toInt() returns character's integer value (character '한' is for Unicode value 54620)
     */
    ch = '한'
    println(ch.toInt())
}
