package com.aoc

class ShuttleSearch(val input: List<String>) {

    private val time: Int = input.first().toInt()
    private val definedBuses: List<Int> = input[1]
        .split(",")
        .filter { it != "x" }
        .map { it.toInt() }
    private val allBuses = input[1]
        .split(",")

    fun findResultForFirstBusMatch(): Int {

        fun noneBusMatch(iteration: Int): Boolean {
            val timeToMatch = time + iteration
            return definedBuses
                .asSequence()
                .map { timeToMatch % it }
                .filter { it == 0 }
                .none()
        }

        fun findBusIdGivenTimeToWait(timeToWait: Int): Int {
            val timeToMatch = time + timeToWait
            return definedBuses.first { timeToMatch % it == 0 }
        }

        val minutesToWait = generateSequence(0, { next -> next + 1 })
            .takeWhile { noneBusMatch(it) }
            .count()

        return minutesToWait * findBusIdGivenTimeToWait(minutesToWait)
    }

    fun findTimestampForBusesDepartMatchingOffsets(): Long {
        val offsetByBuses = allBuses
            .mapIndexed { index, s ->
                val busId = s.toIntOrNull()
                Pair(busId, index)
            }
            .filter { it.first != null }
            .map { Pair(it.first!!.toLong(), it.second.toLong()) }
            .toMap()
        val maxBus = definedBuses.maxByOrNull { it }!!.toLong()
        val maxBusOffset = offsetByBuses.getValue(maxBus)

        fun busesDontMatchOffsets(timeToMatch: Long): Boolean {
            return offsetByBuses
                .any { ((timeToMatch - maxBusOffset) + it.value) % it.key != 0L }
        }

        // take the highest bus value instead !

        return generateSequence(0L, { next -> next + maxBus })
            .takeWhile { busesDontMatchOffsets(it) }
            .last() + (maxBus - maxBusOffset)
    }

}
