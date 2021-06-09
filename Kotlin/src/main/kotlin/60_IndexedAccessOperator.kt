fun main(args: Array<String>): Unit {
    val someone: Someone = Someone("Kotlin", "2016-02-15")
    println(someone[0])
    println(someone[1])
    println(someone[-1])

    someone[0] = "Java"
    println(someone.name)
}

class Someone(var name: String, var birthday: String) {
    operator fun get(position: Int): String {
        return when (position) {
            0 -> name
            1 -> birthday
            else -> "Unknown"
        }
    }

    operator fun set(position: Int, value: String) {
        when (position) {
            0 -> name = value
            1 -> birthday = value
        }
    }
}

/**
 * In kotlin, [] operator supports mutliple operands such as someone[1, 2, 3]
 * someone[1, 2, 3] = "TestValue" is called as below
 * someone.set(1, 2, 3, "TestValue")
 */
