package com.aoc

class TobogganTrajectoryCalculator(grid: List<String>) {
    val areaMap: AreaMap

    init {
        this.areaMap = AreaMap(grid.map { it.toCharArray() })
    }

    fun countTrees(slopeX: Int, slopeY: Int): Int {
        var position = Coordinate(0, 0)
        val elements = mutableListOf<Char>()
        while (position.y < areaMap.bottomLimit) {
            elements.add(getElementAtPosition(position))
            position = move(position, slopeX, slopeY)
        }
        return elements.count { it == '#' }
    }

    data class AreaMap(val cells: List<CharArray>, val bottomLimit: Int, val rightLimit: Int) {
        constructor(cells: List<CharArray>) : this(cells, cells.size, cells[0].size)
    }
    data class Coordinate(val x: Int, val y: Int)

    private fun move(position: Coordinate, slopeX: Int, slopeY: Int): Coordinate {
        val x = (position.x + slopeX).let { if (it > areaMap.rightLimit - 1) it - (areaMap.rightLimit) else it }
        return Coordinate(x, position.y + slopeY)
    }

    private fun getElementAtPosition(position: Coordinate): Char {
        return areaMap.cells[position.y][position.x]
    }
}
