class Building{
    var name = ""
    var date = ""
    var area = 0

    /**
     * 'this' keyword is for indicting object invoking this member function.
     * 'this' keyword can be omitted.
     */
    fun print() {
        println(this.name)
        println(this.date)
        println(area)
    }
}

fun main(args: Array<String>): Unit {
    val building = Building()

    building.name = "BuildingName"
    building.date = "2017-12-03"
    building.area = 120 * 8

    building.print()
}
