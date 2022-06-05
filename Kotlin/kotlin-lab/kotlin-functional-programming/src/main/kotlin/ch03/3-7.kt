package ch03

fun String.head() = first()
fun String.tail() = drop(1)

fun main(args: Array<String>) {
    println(reverse("abcd"))
}

fun reverse(str: String): String = when {
    str.isEmpty() -> ""
    else -> reverse(str.tail()) + str.head()
}

