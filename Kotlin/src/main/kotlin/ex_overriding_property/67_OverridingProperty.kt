package ex_overriding_property

/**
 * You can override 'val' property of superclass as 'var' property in subclass
 * open class AAA(open val number: Int = 0)
 * class BBB: AAA() {
 *      override var number: Int = 0
 *      get() = super.number
 *      set // default setter
 * }
 */
open class AAA {
    open var number = 10
    get() {
        println("AAA number getter called")
        return field
    }
    set(value) {
        println("AAA number setter called")
        field = value
    }
}

class BBB: AAA() {
    override var number: Int
        get() {
            println("BBB number getter called")
            return super.number // Superclass's getter is called
        }
        set(value) {
            println("BBB number setter called")
            super.number = value // Superclass's setter is called
        }
}

fun main(args: Array<String>): Unit {
    val test = BBB()

    println(test.number)
    /**
     * BBB number getter called
     * AAA number getter called
     * 10
     */
    test.number = 5
    /**
     * BBB number setter called
     * AAA number setter called
     */
    println(test.number)
    /**
     * BBB number getter called
     * AAA number getter called
     * 5
     */
    test.number
    /**
     * BBB number getter called
     * AAA number getter called
     */
    println(test.number)
    /**
     * BBB number getter called
     * AAA number getter called
     * 5
     */
}
