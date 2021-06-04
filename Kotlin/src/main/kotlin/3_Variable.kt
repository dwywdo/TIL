/**
 * There's no primitive type in Kotlin. You can see below by integer type is written like 'Int', not 'int'
 * In Kotlin, unless we set null to Kotlin basic type or generic, Java byte code is generated to use pritimitve types
 */
fun main(args: Array<String>): Unit {
    var total: Int = 0 // a mutable variable is decalred by using keyword 'var'

    val a: Int = 10 + 53 - 7 // an immutable variable is declared by using keyword 'val'
    println(a)

    val b: Int = 43 + 75 + a
    println(b)

    total = a + b
    println(total)
}
