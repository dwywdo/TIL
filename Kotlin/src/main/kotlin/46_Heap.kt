fun main(args: Array<String>): Unit {
    /**
     * Memory architecture
     * /----stack----/        /----heap----/
     * /     args    /        /            /
     * /    person   / -----> /    name    /
     * /             /        /    age     /
     * /             /        /            /
     * /             /        /            /
     * /-------------/        /------------/
     *
     * - Object expression (object {...}) creates an object in heap memory
     * - Variable person is storage for reference (called referenced variable)
     */
    val person = object {
        val name: String = "홍길동"
        val age: Int = 36
    }
    println(person.name)
    println(person.age)

}
