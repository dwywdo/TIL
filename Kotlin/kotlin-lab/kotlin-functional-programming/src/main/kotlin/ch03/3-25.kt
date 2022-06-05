package ch03

fun main(args: Array<String>) {
    println(reverse("Hello"))
    println(tailrecReverse("Hello"))

}

tailrec fun tailrecReverse(str: String, acc: String = ""): String = when {
    str.isEmpty() -> acc
    else -> {
        val reversed = str.head() + acc
        tailrecReverse(str.tail(), reversed)
    }
}
