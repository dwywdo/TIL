fun main(args: Array<String>): Unit {
    celciusToFah(27)
    celciusToFahAsExpression(27)
}

/**
 * Unit type corresponds to 'void' in JAVA. However, they're not exactly same
 * It's because Kotlin's 'Unit' is general type defined with 'class'.
 * The function that returns 'Unit' implcitly returns object which has type 'Unit'
 * But, Unit object is a singletone instance so that it's not generated on every function calls
 */
fun celciusToFah(celcius: Int): Unit {
    println(celcius * 1.8 + 32)
    return // can be omitted
}

/**
 * Only one expression exists in function, it can be replaced with just ' = expression' in function declaration
 */
fun celciusToFahAsExpression(celcius: Int) = println(celcius * 1.8 + 32)
