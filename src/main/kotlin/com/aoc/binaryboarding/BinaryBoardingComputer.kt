package com.aoc.binaryboarding

class BinaryBoardingComputer(private val binarySpacePartitioning: String) {

    companion object {
        private val LOWER_HALF: (List<Int>) -> List<Int> =
            { input -> listOf(input[0], computeMiddle(input[1], input[0])) }
        private val UPPER_HALF: (List<Int>) -> List<Int> =
            { input -> listOf(computeMiddle(input[0], input[1]) + 1, input[1]) }

        private fun computeMiddle(x1: Int, x2: Int) = x1.plus(x2).div(2)

        val BINARY_SPACE_PARTITIONING_LETTER_BY_FUNCTION = mapOf(
            Pair('F', LOWER_HALF),
            Pair('B', UPPER_HALF),
            Pair('L', LOWER_HALF),
            Pair('R', UPPER_HALF)
        )
    }

    fun findRangeRow(): List<Int> = findRangeUsingInitialValue(listOf(0, 127))

    fun findRangeSeat(): List<Int> = findRangeUsingInitialValue(listOf(0, 7))

    private fun findRangeUsingInitialValue(range: List<Int>) =
        binarySpacePartitioning
            .map { BINARY_SPACE_PARTITIONING_LETTER_BY_FUNCTION[it] }
            .fold(range) { acc, operation -> operation!!.invoke(acc) }
}
