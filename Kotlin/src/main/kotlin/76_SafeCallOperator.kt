
fun main(args: Array<String>): Unit {
    /**
     * To access property / member function from nullable reference variable, use ?.
     */
    var obj: Building? = null
    obj?.print() // null: Unit? (note that the type is Unit? and value is null)
    obj?.name = "건물" // null: Unit? (setter is also function)

    obj = Building()
    obj?.name = "서울월드컵경기장"
    obj?.date = "2011-11-10"
    obj?.area = 21_6712
    obj?.print()

    /**
     * !! operator forcefully casts Nullable type as Not-null type
     */
    var obj2: Building? = Building()
    obj2!!.name = "서울시청"
    println(obj2!!.name)

    obj2 = null
    // obj2!!.print() // KotlinNullPointerException

    /**
     * Elvis operator ?:
     */
    val number: Int? = null
    println(number ?: 0) // 0

    val number2: Int? = 15
    println(number2 ?: 0) // 15
}
