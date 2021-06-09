fun main(args: Array<String>) {
    var first = "Hello"
    var second = "World"
    first += second
}

/**
 *   /----stack----/        /----heap-------/
 *   /     args    /        /               /
 *   /    first    / -----> /   "hello"     /
 *   /   second    / -----> /   "age"       /
 *   /             /        /               /
 *   /             /        /               /
 *   /-------------/        /---------------/
 *
 *   /----stack----/        /----heap-------/
 *   /     args    /        /               /
 *   /    first    / ---    /   "hello"     /
 *   /   second    / --|--> /   "age"       /
 *   /             /   |--> / "hello world" /
 *   /             /        /               /
 *   /-------------/        /---------------/
 */
