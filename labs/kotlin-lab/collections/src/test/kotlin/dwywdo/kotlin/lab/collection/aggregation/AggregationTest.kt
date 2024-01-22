package dwywdo.kotlin.lab.collection.aggregation

import org.junit.jupiter.api.Test

class AggregationTest {
    @Test
    fun `Basic operations for aggregation`() {
        val numbers = listOf(6, 42, 10, 4)
        println("Count = ${numbers.count()}")
        println("Max = ${numbers.maxOrNull()}")
        println("Min = ${numbers.minOrNull()}")
        println("Average = ${numbers.average()}")
        println("Sum = ${numbers.sum()}")
    }

    @Test
    fun `Selector for max, min operations`() {
        val numbers = listOf(5, 42, 10, 4, 9, 17)
        /**
         * minByOrNull -> Selector 적용 -> 적용 결과 값을 비교 -> 가장 작은 값을 산출하는 원래의 요소 값 반환
         * 여러 개라면 가장 처음 것을 반환
         */
        println("numbers.minByOrNull { it % 3 } = ${numbers.minByOrNull { it % 3 }}") // 42
        println("numbers.minByOrNull { it % 4 } = ${numbers.minByOrNull { it % 4 }}") // 4
        println("numbers.minByOrNull { it % 5 } = ${numbers.minByOrNull { it % 5 }}") // 5
        println("numbers.minByOrNull { it % 10 } = ${numbers.minByOrNull { it % 10 }}") // 10

        /**
         * minOfOrNull -> Selector 적용 -> 적용 결과 값을 비교 -> 적용 결과 값 중 가장 작은 값을 반환
         * 여러 개라면 가장 처음 것을 반환? 이런 문구 자체가 없다
         * 왜? 처음 것이 사실 상 의미있는지는 모르겠음. 어차피 같은 값을 반환하기 때문
         */
        println("numbers.minOfOrNull { it % 3 } = ${numbers.minOfOrNull { it % 3 }}") // 0
        println("numbers.minOfOrNull { it % 4 } = ${numbers.minOfOrNull { it % 4 }}") // 0
        println("numbers.minOfOrNull { it % 7 } = ${numbers.minOfOrNull { it % 7 }}") // 0
        println("numbers.minOfOrNull { it % 8 } = ${numbers.minOfOrNull { it % 8 }}") // 1

        /**
         * maxWithOrNull -> Comparator 기반으로 최댓값을 갖는 원래의 요소 반환
         * 여러 개라면 가장 처음 것을 반환
         */
        val strings = listOf("one", "two", "three", "four")
        println("strings.maxWithOrNull(compareBy {it.length }) = ${strings.maxWithOrNull(compareBy { it.length })}")
    }

    @Test
    fun `Reduce and Fold`() {
        val numbers = listOf(5, 2, 10, 4)
        val simpleSum = numbers.reduce { sum, element -> sum + element }
        println("simpleSum = $simpleSum")

        val sumDoubled = numbers.fold(0) { sum, element -> sum + element * 2 }
        println("sumDoubled = $sumDoubled")

        val sumDoubledWithSumOf = numbers.sumOf { it * 2 }
        println("sumDoubledWithSumOf = $sumDoubledWithSumOf")

        /**
         * Fold는 초기값을 정해줌으로써 각 요소의 두 배를 합한 값을 계산할 수 있지만
         * 같은 계산식을 Reduce에 적용하게 되면 첫 요소는 두 배로 계산되지 않는다.
         */
        val sumDoubledReduce = numbers.reduce { sum, element -> sum + element * 2 }
        println("[WARN] sumDoubledReduce: Wrong. First element isn't doubled = $sumDoubledReduce")
    }

    @Test
    fun `Reduce and Fold - Reversed with -Right operations`() {
        val numbers = listOf(5, 2, 10, 4)
        val sumDoubledRight = numbers.foldRight(0) { element, sum -> sum + element * 2}
        println("sumDoubledRight = $sumDoubledRight")

        val sumDoubledReduceRight = numbers.reduceRight { element, sum -> sum + element * 2 }
        println("[WARN] sumDoubledReduceRight: Wrong. Last element isn't doubled = $sumDoubledReduceRight")
    }

    @Test
    fun `Reduce and Fold - Index as parameter with -Indexed`() {
        val numbers = listOf(5, 2, 10, 4)
        val sumEven = numbers.foldIndexed(0) { idx, sum, element -> if (idx % 2 == 0) sum + element else sum}
        println("sumEven = $sumEven")

        /**
         * Index가 역순으로 계산된다.
         */
        val sumEvenRight = numbers.foldRightIndexed(0) { idx, sum, element -> if (idx % 2 == 0) sum + element else sum}
        println("sumEvenRight = $sumEvenRight")
    }
}
