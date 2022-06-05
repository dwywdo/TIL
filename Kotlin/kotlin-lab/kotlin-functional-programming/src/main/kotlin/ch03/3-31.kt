package ch03

fun main(args: Array<String>) {
    println(setOf(1, 2, 3).powerset())
}

fun <T> Collection<T>.head() = first()
fun <T> Collection<T>.tail() = drop(1).toSet()

fun <T> powerset(s: Set<T>): Set<Set<T>> = when {
    s.isEmpty() -> setOf(setOf())
    else -> {
        val head = s.head()
        val restSet = powerset(s.tail())
        restSet + restSet.map { setOf(head) + it }.toSet()
    }
}

tailrec fun <T> tailrecPowerset(s: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
    s.isEmpty() -> acc
    else -> tailrecPowerset(s.tail(), acc + acc.map { it + s.head() })
}

fun <T> Collection<T>.powerset(): Set<Set<T>> = tailrecPowerset(this, setOf(setOf()))

