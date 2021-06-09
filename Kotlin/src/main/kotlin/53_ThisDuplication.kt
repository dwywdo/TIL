fun main(args: Array<String>) {
    val a = AAA()
    a.memberFunc(53)
}

class AAA {
    var num = 15

    fun memberFunc(num: Int) {
        println(num)
        println(this.num)
    }
}
