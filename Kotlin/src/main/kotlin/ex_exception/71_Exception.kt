package ex_exception

fun main(args: Array<String>): Unit {
    try {
        val str = "abcd"
        val num = str.toInt()
        println(num)
    } catch(e: NumberFormatException) {
        println("Couldn't change to integer")
    } finally {
        println("Exit")
    }

    try {
        something()
    } catch(e: Exception) {
        println(e.message)
    }
}

fun something() {
    val num1 = 10
    val num2 = 0
    div(num1, num2)
}

fun div(a: Int, b: Int) : Int{
    if (b == 0) {
        throw Exception("Can't divide by 0") // throw 'Throwable type expression'. Exception inherits from Throwable
        // String inside Exception's constructor is message representing exception type
    }
    return a / b
}
