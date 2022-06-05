package ch03

fun tailRecursion(n: Int): Int = when (n) {
    0 -> 0
    else -> tailRecursion(n - 1)
}

fun headRecursion(n: Int): Int = when {
    n > 0 -> headRecursion(n - 1)
    else -> 0
}
