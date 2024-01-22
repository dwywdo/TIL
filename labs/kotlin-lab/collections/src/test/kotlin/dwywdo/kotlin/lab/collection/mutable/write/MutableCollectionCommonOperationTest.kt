package dwywdo.kotlin.lab.collection.mutable.write

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * MutableCollection's Interfaces for WRITE operations
 */
class MutableCollectionCommonOperationTest {
    /**
     * APIs for adding element(s) into collection
     */
    @Test
    fun addTest() {
        val numbers = mutableListOf(1, 2, 3, 4)
        numbers.add(5)
        println("numbers = $numbers")
    }

    @Test
    fun addAllTest() {
        val numbers = mutableListOf(1, 2, 5, 6)
        numbers.addAll(arrayOf(7, 8))
        println("numbers = $numbers")

        numbers.addAll(2, setOf(3, 4))
        println("numbers = $numbers")

        val addedNumbers = mutableListOf(9, 10, 11, 12)
        numbers.addAll(addedNumbers)
        println("numbers = $numbers")
    }

    @Test
    fun addWithOperatorTest() {
        val numbers = mutableListOf("one", "two")
        numbers += "three"
        println("numbers = $numbers")
        numbers += listOf("four", "five")
        println("numbers = $numbers")
    }

    /**
     * APIs for removing element(s) into mutable collection
     */
    @Test
    fun removeTest() {
        val numbers = mutableListOf(1, 2, 3, 4, 3)
        numbers.remove(3) // Removes the first `3`
        println("numbers = $numbers")
        numbers.remove(5) // Removes nothing
        println("numbers = $numbers")
    }

    @Test
    fun removeAllTest() {
        val numbers = mutableListOf(1, 2, 3, 4, 3)
        numbers.removeAll(listOf(1, 3))
        println("numbers = $numbers")
    }

    @Test
    fun removeAllWithPredicatesTest() {
        val numbers = mutableListOf(1, 2, 3, 4, 3)
        numbers.removeAll { it % 2 == 0 }
        println("numbers = $numbers")
    }


    @Test
    fun retainAllTest() {
        val numbers = mutableListOf(1, 2, 3, 4, 3)
        numbers.retainAll(listOf(1, 3))
        println("numbers = $numbers")
    }

    @Test
    fun retainAllWithPredicatesTest() {
        val numbers = mutableListOf(1, 2, 3, 4, 3)
        numbers.retainAll { it >= 3 }
        println("numbers = $numbers")
    }

    @Test
    fun clearTest() {
        val numbers = mutableListOf(1, 2, 3, 4)
        numbers.clear()
        println("numbers = $numbers")
        val numbersSet = mutableSetOf("one", "two", "three", "four")
        numbersSet.clear()
        println("numbersSet = $numbersSet")
    }

    @Test
    fun removeWithOperatorTest() {
        val numbers = mutableListOf("one", "two", "three", "four", "five")
        numbers -= "three"
        println("numbers = $numbers")
        numbers -= listOf("four", "five")
        println("numbers = $numbers")
    }
}
