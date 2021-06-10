package ex_polymorphism

open class AAA {
    open fun hello() = println("AAA")
}

class BBB: AAA() {
    override fun hello() = println("BBB")
}

fun main(args: Array<String>): Unit {
    val one = AAA()
    val two = BBB()
    val three: AAA = two

    one.hello()
    two.hello()
    three.hello()
}
