import kotlin.text.split

fun main() {
    fun List<String>.toLongs(): List<Long> =
        this.first().split(",")
            .flatMap { it.split("-").map(String::toLong).let { (start, end) -> start..end } }

    fun isInvalidId(id: String, chunkSizes: Iterable<Int>): Boolean =
        chunkSizes.any { chunkSize ->
            val chunks = id.chunked(chunkSize)
            chunks.all { it == chunks[0] }
        }

    fun part1(input: List<String>): Long =
        input.toLongs()
            .map { it.toString() }
            .filter { it.length % 2 == 0 && isInvalidId(it, listOf(it.length / 2)) }
            .sumOf { it.toLong() }

    fun part2(input: List<String>): Long =
        input.toLongs()
            .map { it.toString() }
            .filter { isInvalidId(it, 1..it.length / 2) }
            .sumOf { it.toLong() }

    check(part1(listOf("11-22")) == 33L)
    check(part1(listOf("95-115")) == 99L)

    check(part2(listOf("2121212118-2121212124")) == 2121212121L)
    check(part2(listOf("824824821-824824827")) == 824824824L)
    check(part2(listOf("38593856-38593862")) == 38593859L)

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
