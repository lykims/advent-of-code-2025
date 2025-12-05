fun main() {
    fun countNeighbours(input: MutableList<CharArray>, i: Int, j: Int): Long {
        var count = 0L
        for (di in -1..1) {
            for (dj in -1..1) {
                if (di == 0 && dj == 0) {
                    continue
                }
                val ni = i + di
                val nj = j + dj
                if (ni < 0 || ni >= input.size || nj < 0 || nj >= input[i].size) {
                    continue
                }
                if (input[ni][nj] == '@') {
                    count++
                }
            }
        }
        return count
    }

    fun part1(input: List<String>): Long {
        val mutableInput = input.map { it.toCharArray() }.toMutableList()

        var count = 0L
        for (i in input.indices) {
            for (j in input[i].indices) {
                val char = input[i][j]
                if (char == '@') {
                    val neighbourCount = countNeighbours(mutableInput, i, j)
                    if (neighbourCount < 4) {
                        count++
                    }
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Long {
        val mutableInput = input.map { it.toCharArray() }.toMutableList()

        var count = 0L
        var hasRollsToRemove = true

        while (hasRollsToRemove) {
            var iterationCount = 0L

            for (i in mutableInput.indices) {
                for (j in mutableInput[i].indices) {
                    val char = mutableInput[i][j]
                    if (char == '@') {
                        val neighbourCount = countNeighbours(mutableInput, i, j)
                        if (neighbourCount < 4) {
                            mutableInput[i][j] = '.'
                            iterationCount++
                        }
                    }
                }
            }

            count += iterationCount

            if (iterationCount == 0L) {
                hasRollsToRemove = false
            }
        }

        return count
    }

    check(part1(listOf(".@.@", ".@..", "@@@.")) == 5L)
    check(part2(listOf(".@.@", ".@..", "@@@.")) == 6L)

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13L)
    check(part2(testInput) == 43L)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
