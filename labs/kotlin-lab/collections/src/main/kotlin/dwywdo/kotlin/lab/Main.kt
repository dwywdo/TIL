package dwywdo.kotlin.lab

fun main() {
    println("Hi")
    val list = ArrayList<String>()
    list.add("Item")
    val size = list.size
    val item = list[0]

    val nullable: String? = item
    val notNull: String = item
}
