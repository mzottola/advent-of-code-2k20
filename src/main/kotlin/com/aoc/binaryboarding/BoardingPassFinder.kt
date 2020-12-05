package com.aoc.binaryboarding

class BoardingPassFinder(private val binarySpacePartitioning: String) {

    fun computeSeatId(): Int {
        val partition = binarySpacePartitioning.partition { it == 'B' || it == 'F' }
        val row = BinaryBoardingComputer(partition.first).findRangeRow().first()
        val seat = BinaryBoardingComputer(partition.second).findRangeSeat().first()
        return row.times(8).plus(seat)
    }

}
