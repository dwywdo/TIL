package ch04

/**
 * 부분 함수를 만들기 위한 추상화된 클래스가 스칼라에는 있지만, 코틀린에는 없다.
 * 부분 함수 생성을 위한 클래스를 만들어보자.
 */

class PartialFunction<in P, out R>(
    private val condition: (P) -> Boolean,
    private val f: (P) -> R
) : (P) -> R {
    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported")
    }
    fun isDefinedAt(p: P): Boolean = condition(p)
}
