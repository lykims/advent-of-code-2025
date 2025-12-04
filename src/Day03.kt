fun main() {
    fun findNextMax(s: String, start: Int, remaining: Int, joltageDigits: MutableList<String>) {
        if (remaining == 0) return

        var max = 0
        var maxIndex = 0

        val end = s.length - remaining + 1
        for (i in start until end) {
            val digit = s[i].digitToInt()
            if (digit > max) {
                max = digit
                maxIndex = i
            }
        }
        
        joltageDigits.add(max.toString())
        findNextMax(s, maxIndex + 1, remaining - 1, joltageDigits)
    }

    fun getJoltage(bank: String, digitLength: Int): Long {
        val joltageDigits: MutableList<String> = mutableListOf()
        findNextMax(bank, 0, digitLength, joltageDigits)
        return joltageDigits.joinToString("").toLong()
    }

    fun part1(input: List<String>): Long {
        val joltages: MutableList<Long> = mutableListOf()
        for (bank in input) {
            joltages.add(getJoltage(bank, 2))
        }
        return joltages.sum()
    }

    fun part2(input: List<String>): Long {
        val joltages: MutableList<Long> = mutableListOf()
        for (bank in input) {
            joltages.add(getJoltage(bank, 12))
        }
        return joltages.sum()
    }

    check(part1(listOf("987654321111111")) == 98L)
    check(part1(listOf("811111111111119")) == 89L)
    check(part1(listOf("234234234234278")) == 78L)
    check(part1(listOf("818181911112111")) == 92L)

    check(part2(listOf("987654321111111")) == 987654321111L)
    check(part2(listOf("811111111111119")) == 811111111119L)
    check(part2(listOf("234234234234278")) == 434234234278L)
    check(part2(listOf("818181911112111")) == 888911112111L)

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357L)
    check(part2(testInput) == 3121910778619L)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
