class Time(val second: Int) { // Primary Constructor
    init {
        println("Init Block 1")
    }

    /**
     * this(..): call another constructor declared in this class
     */
    constructor(minute: Int, second: Int): this(minute * 60 + second) {
        println("Secondary Constructor 1")
    }

    constructor(hour: Int, minute: Int, second: Int): this(hour * 60 + minute, second) {
        println("Secondary Constructor 2")
    }

    init {
        println("Init Block 2")
    }
}

fun main(args: Array<String>): Unit {
    println("${Time(15, 6).second} 초")
    println("${Time(6,3, 6).second} 초")
}
