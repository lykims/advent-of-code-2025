fun main() {
    fun List<String>.toInventory(): Inventory {
        val freshIdRanges: MutableList<Range> = mutableListOf()
        val ingredientIds: MutableList<Long> = mutableListOf()

        for (line in this) {
            if (line.isBlank()) continue

            if (line.contains('-')) {
                val ids = line.split('-')
                val range = Range(ids[0].toLong(), ids[1].toLong())
                freshIdRanges.add(range)
            } else {
                ingredientIds.add(line.toLong())
            }
        }

        return Inventory(freshIdRanges, ingredientIds)
    }

    fun part1(input: List<String>): Long {
        val inventory = input.toInventory()

        var count = 0L

        for (id in inventory.ingredientIds) {
            for (range in inventory.freshIdRanges) {
                if (id >= range.start && id <= range.end) {
                    count++
                    break
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Long {
        val sortedRanges = input.toInventory().freshIdRanges.sortedBy { it.start }
        
        if (sortedRanges.isEmpty()) return 0L
        
        val mergedRanges = mutableListOf<Range>()
        var currentRange = sortedRanges[0]
        
        for (i in 1 until sortedRanges.size) {
            val nextRange = sortedRanges[i]
            if (nextRange.start <= currentRange.end + 1) {
                currentRange = Range(
                    currentRange.start,
                    maxOf(currentRange.end, nextRange.end)
                )
            } else {
                mergedRanges.add(currentRange)
                currentRange = nextRange
            }
        }
        mergedRanges.add(currentRange)
        
        return mergedRanges.sumOf { it.end - it.start + 1 }
    }

    check(part1(listOf("2-4", "", "3")) == 1L)
    check(part1(listOf("2-4", "", "5")) == 0L)
    check(part2(listOf("2-5", "", "5")) == 4L)
    check(part2(emptyList()) == 0L)

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3L)
    check(part2(testInput) == 14L)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

data class Range(
    val start: Long,
    val end: Long,
)

data class Inventory(
    val freshIdRanges: List<Range>,
    val ingredientIds: List<Long>
)
