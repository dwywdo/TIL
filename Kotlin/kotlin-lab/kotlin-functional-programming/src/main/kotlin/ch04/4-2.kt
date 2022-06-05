package ch04

fun main(args: Array<String>) {
    val calcSum = Sum()
    val calcMinus = Minus()
    val calcProduct = Product()

    println(calcSum.calc(1, 5))
    println(calcMinus.calc(5, 2))
    println(calcProduct.calc(4, 2))
}

interface Calcable {
    fun calc(x: Int, y: Int): Int
}

class Sum: Calcable {
    override fun calc(x: Int, y: Int): Int {
       return x + y
    }
}

class Minus: Calcable {
    override fun calc(x: Int, y: Int): Int {
       return x - y
    }
}

class Product: Calcable {
    override fun calc(x: Int, y: Int): Int {
       return x * y
    }
}
