package ch03

fun main(args: Array<String>) {
    println(trampoline(evenBounce(999999)))
    println(trampoline(oddBounce(999999)))
}

fun oddBounce(n: Int): Bounce<Boolean> = when (n) {
    0 -> Done(false)
    else -> More { evenBounce(n-1) }
}

fun evenBounce(n: Int): Bounce<Boolean> = when (n) {
    0 -> Done(true)
    else -> More { oddBounce(n-1) }
}

sealed class Bounce<A>
data class Done<A>(val result: A): Bounce<A>()
data class More<A>(val thunk: () -> Bounce<A>): Bounce<A>()

tailrec fun <A> trampoline(bounce: Bounce<A>): A = when (bounce) {
    is Done -> bounce.result
    is More -> trampoline(bounce.thunk())
}


