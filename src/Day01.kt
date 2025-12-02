enum class Direction { L, R }

fun main() {
    fun part1(input: List<String>, startDial: Int): Int {
        var dial = startDial
        var zeroCount = 0

        for (turn in input) {
            val direction = Direction.valueOf(turn[0].toString())
            val distance = turn.substring(1).toInt()

            val clicks = when (direction) {
                Direction.L -> 100 - distance
                Direction.R -> distance
            }

            dial = (dial + clicks) % 100
            if (dial == 0) {
                zeroCount++
            }
        }

        return zeroCount
    }

    fun part2(input: List<String>, startDial: Int): Int {
        var dial = startDial
        var zeroCount = 0

        for (turn in input) {
            val direction = Direction.valueOf(turn[0].toString())
            val distance = turn.substring(1).toInt()

            repeat(distance) {
                dial = when (direction) {
                    Direction.L -> (dial - 1 + 100) % 100
                    Direction.R -> (dial + 1) % 100
                }
                if (dial == 0) {
                    zeroCount++
                }
            }
        }

        return zeroCount
    }

    check(part1(listOf("L68", "L30", "R48"), 50) == 1)
    check(part1(listOf("L17"), 17) == 1)
    check(part1(listOf("R17"), 83) == 1)

    val testInput = readInput("Day01_test")
    check(part1(testInput, 50) == 3)
    check(part2(testInput, 50) == 6)

    val input = readInput("Day01")
    part1(input, 50).println()
    part2(input, 50).println()
}
