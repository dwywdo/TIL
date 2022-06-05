package ch04

fun main(args: Array<String>) {
    /**
     * 연습문제 4-1
     */
    // Test for invokeOrElse
    val condition: (Int) -> Boolean = { it in 1..3 }
    val body: (Int) -> String = {
        when (it) {
            1 -> "One!"
            2 -> "Two!"
            3 -> "Three!"
            else -> throw IllegalArgumentException()
        }
    }
    val oneTwoThree = PartialFunction2(condition, body)
    println(oneTwoThree.invokeOrElse(10, "10"))

    // Test for orElse
    val condition2: (Int) -> Boolean = { it in 3..6 }
    val body2: (Int) -> String = {
        "wow $it"
    }
    val conditionPartial = PartialFunction2(condition2, body2)

    val test1 = conditionPartial.orElse(oneTwoThree)(1)
    val test2 = conditionPartial.orElse(oneTwoThree)(3)
    val test3 = conditionPartial.orElse(oneTwoThree)(4)
    //val test4 = conditionPartial.orElse(oneTwoThree)(100)

    println(test1)
    println(test2)
    println(test3)
    //println(test4)

    /**
     * 연습문제 4-2
     */
    val func = {a: Int, b: Int, c: Int -> a + b + c }

    val partialAppliedFunc1 = func.partial1(1, 2)
    val result1 = partialAppliedFunc1(3)

    val partialAppliedFunc2 = func.partial2(1, 2)
    val result2 = partialAppliedFunc2(2)

    val partialAppliedFunc3 = func.partial3(2, 2)
    val result3 = partialAppliedFunc3(3)

    println(result1)
    println(result2)
    println(result3)

    /**
     * 연습문제 4-3
     */
    println(max(5, 3))
    println(curryingMax(5)(3))

    /**
     * 연습문제 4-4
     */
    val min = { a: Int, b: Int -> if (a < b) a else b}
    val curried = min.curried()
    println(curried(5)(3))

    /**
     * 연습문제 4-5
     */
    val maxFunc = { list: List<Int> -> list.maxOf {it} }
    val powerFunc = { i: Int -> i * i }
    val result = powerFunc(maxFunc(listOf(1, 3, 4, 8)))
    println(result)
    val composed = powerFunc compose maxFunc
    println(composed(listOf(1, 3, 4, 8)))
}

/**
 * 4-1. 코드 4-12에서 구현한 PartialFunction 클래스에 invokeOrElse 함수와
 * orElse 함수를 추가해보자. 각 함수의 프로토타입은 다음과 같다.
 * fun invokeOrElse(p: P, default: R): R
 * fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R>
 *
 * invokeOrElse 함수는 입력값 p가 조건에 맞지 않을 때 기본값 default를 반환한다.
 * orElse 함수는 PartialFunction의 입력값 p가 조건에 맞으면 PartialFunction을
 * 그대로 반환하고(this), 조건에 맞지 않으면 that을 반환한다.
 */

class PartialFunction2<P, R>(
    private val condition: (P) -> Boolean,
    private val f: (P) -> R
) : (P) -> R {
    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported")
    }

    private fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(p: P, default: R): R = when {
        condition(p) -> f(p)
        else -> default
    }

    fun orElse(that: PartialFunction2<P, R>): PartialFunction2<P, R> =
        PartialFunction2(
            { it: P -> this.isDefinedAt(it) || that.isDefinedAt(it) },
            { it: P ->
                when {
                    this.isDefinedAt(it) -> this(it)
                    that.isDefinedAt(it) -> that(it)
                    else -> throw IllegalArgumentException("$it isn't defined")
                }
            }
        )
}

/**
 * 4-2. 매개변수 3개를 받는 부분 적용 함수 3개를 직접 구현하라.
 */
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1, p2: P2): (P3) -> R {
    return { p3 -> this(p1, p2, p3) }
}
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p1: P1, p3: P3): (P2) -> R {
    return { p2 -> this(p1, p2, p3) }
}
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p2: P2, p3: P3): (P1) -> R {
    return { p1 -> this(p1, p2, p3) }
}

/**
 * 4-3. 두 개의 매개변수를 받아서 큰 값을 반환하는 max 함수를, 커링을 사용할 수 있도록 구현하라.
 */
fun max(a: Int, b: Int): Int =
    if (a > b) a else b

fun curryingMax(a: Int) = { b: Int -> if (a > b) a else b }

/**
 * 4-4. 두 개의 매개변수를 받아서 작은 값을 반환하는 min 함수를 curried()를 사용해서 작성해라
 */
fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = {
    p1: P1 -> {
        p2: P2 -> this(p1, p2)
    }
}

/**
 * 4-5. 숫자(Int)의 리스트를 받아서 최댓값의 제곱을 구하는 함수를 작성해보자.
 */
