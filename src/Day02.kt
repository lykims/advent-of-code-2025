import kotlin.text.split

fun main() {
    fun isInvalidId(id: String): Boolean {
        if (id.length % 2 != 0) {
            return false
        }

        var i = 0
        var j = id.length / 2

        while (i < j && j < id.length) {
            if (id[i] != id[j]) {
                return false
            }
            i++
            j++
        }

        return true
    }

    fun isInvalidId2(id: String): Boolean {
        val substringsByLength = IntRange(1, id.length / 2)
            .map { id.chunked(it) }

        for (substrings in substringsByLength) {
            val substring = substrings[0]
            if (substrings.all { it == substring }) {
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Long {
        val ranges = input[0].split(",")

        val invalidIds: MutableList<Long> = mutableListOf()

        for (range in ranges) {
            val idRange = range.split("-")
            val start = idRange[0].toLong()
            val end = idRange[1].toLong()

            for (i in start..end) {
                val id = i.toString()
                if (isInvalidId(id)) {
                    invalidIds.add(i)
                }
            }
        }

        return invalidIds.sumOf { it }
    }

    fun part2(input: List<String>): Long {
        val ranges = input[0].split(",")

        val invalidIds: MutableList<Long> = mutableListOf()

        for (range in ranges) {
            val idRange = range.split("-")
            val start = idRange[0].toLong()
            val end = idRange[1].toLong()

            for (i in start..end) {
                val id = i.toString()
                if (isInvalidId2(id)) {
                    invalidIds.add(i)
                }
            }
        }

        return invalidIds.sumOf { it }
    }

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
