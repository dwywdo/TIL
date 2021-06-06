fun main(args: Array<String>): Unit {
    println(cToF(30))
    println(getAverage(89, 96))
}

fun cToF(celcius: Int): Double {
    return celcius * 1.8 + 32
}

fun getAverage(a: Int, b: Int): Double {
    return (a + b) / (2.0)
}
