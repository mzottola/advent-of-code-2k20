package com.aoc.adapterarray

class JoltageAdapterComputer(input: List<String>) {

    data class DifferenceResult(val d1: Int, val d2: Int, val d3: Int)

    private val joltageRatings = input.map { it.toInt() }

    fun findDifferences(): DifferenceResult {
        val occurencesByNumber = createDifferences()
            .groupingBy { it }
            .eachCount()
        return DifferenceResult(
            occurencesByNumber.getOrDefault(1, 0),
            occurencesByNumber.getOrDefault(2, 0),
            occurencesByNumber.getOrDefault(3, 0) + 1
        )
    }

    private fun createDifferences(): List<Int> {
        val joltageRatingsDesc = joltageRatings.sortedDescending()
        return joltageRatingsDesc
            .mapIndexed { index, i ->
                when (index) {
                    joltageRatingsDesc.size - 1 -> {
                        i
                    }
                    else -> {
                        i - joltageRatingsDesc[index + 1]
                    }
                }
            }
    }

    fun findMaxDistinctWays(): Long {
        val toMutableList = joltageRatings.toMutableList()
        toMutableList.add(0, 0)
        val differences = createDifferencesTemp(toMutableList)

        // At the maximum, a serie of contiguous numbers will be 5
        // By building the next 'configuousNumberOccurences', the value is "number - 1"
        val numberOfCombinationsByOccurrences = mapOf<Long, Long>(
            Pair(2, 2),
            Pair(3, 4),
            Pair(4, 7),
        )
        var count = 0L
        val contiguousNumberOccurrences = mutableListOf<Long>()

        for (element in differences) {
            if (element == 3 || element == 99) {
                contiguousNumberOccurrences.add(count)
                count = 0L
            } else {
                count++
            }
        }

        return contiguousNumberOccurrences
            .map { numberOfCombinationsByOccurrences.getOrDefault(it, 1) }
            .reduce { acc, d -> acc * d }
    }

    private fun createDifferencesTemp(toMutableList: List<Int>): List<Int> {
        val joltageRatingsSorted = toMutableList.sorted()
        return joltageRatingsSorted
            .mapIndexed { index, i ->
                when (index) {
                    joltageRatingsSorted.size - 1 -> {
                        // hack
                        99
                    }
                    else -> {
                        joltageRatingsSorted[index + 1] - i
                    }
                }
            }
    }

}
