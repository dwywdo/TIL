package dwywdo.kotlin.lab.collection.mutable.write

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.math.sign
import kotlin.test.assertEquals

class ListSpecificOperationTest {
    /**
     * Common operations available for List
     */
    @Test
    fun retrieveElementsByIndexWithCommonOperationTest() {
        val numbers = listOf(1, 2, 3, 4, 5)
        println("numbers.elementAt(0) = ${numbers.elementAt(0)}")
        println("numbers.first() = ${numbers.first()}")
        println("numbers.last() = ${numbers.last()}")
    }

    /**
     * List-specific operations
     */
    @Test
    fun retrieveElementsByIndexWithSpecificOperationTest() {
        val numbers = listOf(1, 2, 3, 4, 5)
        println("numbers[1] = ${numbers[1]}")
        println("numbers.get(1) = ${numbers.get(1)}")
        println("numbers.getOrElse(10, { it }) = ${numbers.getOrElse(10) { it * 10 }}")
        println("numbers.getOrNull(10) = ${numbers.getOrNull(10)}")

        assertThrows<IndexOutOfBoundsException> { println("numbers.get(10) = ${numbers.get(10)}") }
    }

    @Test
    fun retrieveListPartsWithSpecificOperationTest() {
        val numbers = (0..13).toList()
        println("numbers.subList(3, 6) = ${numbers.subList(3, 6)}")
    }

    @Test
    fun linearSearchTest() {
        val numbers = listOf(1, 2, 3, 4, 2, 5)
        println("numbers.indexOf(2) = ${numbers.indexOf(2)}")
        println("numbers.lastIndexOf(2) = ${numbers.lastIndexOf(2)}")
        assertEquals(-1, numbers.indexOf(10))
    }
    
    @Test
    fun linearSearchWithPredicatesTest() {
        val numbers = listOf(1, 2, 3, 4)
        println("numbers.indexOfFirst { it > 2 } = ${numbers.indexOfFirst { it > 2 }}")
        println("numbers.indexOfLast { it % 2 == 1 } = ${numbers.indexOfLast { it % 2 == 1 }}")
    }

    @Test
    fun binarySearchTest() {
        val numbers = mutableListOf("one", "two", "three", "four")
        numbers.sort()
        println("numbers = $numbers")
        println("numbers.binarySearch(\"two\") } = ${numbers.binarySearch("two") }}")
        println("numbers.binarySearch(\"z\") = ${numbers.binarySearch("z")}")
        println("numbers.binarySearch(\"two\", 0, 2) = ${numbers.binarySearch("two", 0, 2)}")
    }

    @Test
    fun binarySearchWithComparator() {
        val productList = listOf(
            Product("WebStorm", 49.0),
            Product("AppCode", 99.0),
            Product("DotTrace", 129.0),
            Product("ReSharper", 149.0),
        )

        println("productList.binarySearch(Product(\"AppCode\", 99.0), compareBy<Product>{ it.price }.thenBy { it.name } ) = " +
                "${productList.binarySearch(Product("AppCode", 99.0), compareBy<Product>{ it.price }.thenBy { it.name })}"
        )
    }

    @Test
    fun binarySearchWithComparisonTest() {
        val productList = listOf(
            Product("WebStorm", 49.0),
            Product("AppCode", 99.0),
            Product("DotTrace", 129.0),
            Product("ReSharper", 149.0),
        )

        println("productList.binarySearch { priceComparison(it, 99.0) } = ${productList.binarySearch { priceComparison(it, 99.0) }}")
    }

    private fun priceComparison(product: Product, price: Double) = sign(product.price - price).toInt()

    data class Product(val name: String, val price: Double)

    /**
     * List Write Operations
     */
    @Test
    fun addTest() {
        val numbers = mutableListOf("one", "five", "six")
        numbers.add(1, "two")
        numbers.addAll(2, listOf("three", "four"))
        println("numbers = $numbers")
    }

    @Test
    fun setTest() {
        val numbers = mutableListOf("one", "five", "three")
        numbers[1] = "two"
        numbers.set(2, "four")
        println("numbers = $numbers")
    }

    @Test
    fun fillTest() {
        val numbers = mutableListOf(1, 2, 3, 4)
        numbers.fill(3)
        println("numbers = $numbers")
    }

    @Test
    fun removeTest() {
        val numbers = mutableListOf(1, 2, 3, 4, 3)
        numbers.removeAt(1)
        println("numbers = $numbers")
    }

    /**
     * List Sort Operations
     */
    @Test
    fun sortTest() {
        val numbers = mutableListOf("one", "two", "three", "four")
        numbers.sort()
        println("numbers = $numbers")

    }

}
