package ch03

fun repeatUsingSequence(n: Int): Sequence<Int> = generateSequence(n) { it }
