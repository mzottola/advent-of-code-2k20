package com.aoc

class TobogganTrajectoryCalculator(grid: List<String>) {
    data class AreaMap(val cells: List<CharArray>, val rightLimit: Int) {
        constructor(cells: List<CharArray>) : this(cells, cells.first().size)
    }

    val areaMap: AreaMap

    init {
        this.areaMap = AreaMap(grid.map { it.toCharArray() })
    }

    fun countTrees(slopeX: Int, slopeY: Int): Int {
        return areaMap.cells
            .filterIndexed { index, chars -> index != 0 && index % slopeY == 0 }
            .mapIndexed { index, chars -> chars[((index + 1) * slopeX) % areaMap.rightLimit] }
            .count { it == '#' }
    }
}
