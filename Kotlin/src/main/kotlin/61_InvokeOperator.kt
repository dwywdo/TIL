fun main(args: Array<String>): Unit {
    val product = Product(762443, "Kotlin 200")
    product(108)
}

class Product(val id: Int, val name: String) {
    operator fun invoke(value: Int) {
        println(value)
        println("id: $id\nname: $name")
    }
}
