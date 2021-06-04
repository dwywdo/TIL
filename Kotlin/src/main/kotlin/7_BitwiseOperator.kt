fun main(args: Array<String>): Unit {
    println(15 and 7) // 15 & 7
    println(5 or 2) // 5 | 2
    println(15 xor 5) // 15 ^ 5
    println(32767.inv()) // ~32767
    println(1 shl 3) // 1 << 3
    println(8 shr 1) // 8 >> 1
    println(-17 ushr 2) // Preserving sign (+/-) and shift by 2 bits: -17 >>> 2
}
