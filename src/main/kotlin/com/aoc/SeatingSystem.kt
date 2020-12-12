package com.aoc

class SeatingSystem(val input: List<String>) {

    private val OUTSIDE_CHARACTER = '?'
    private val area = input.map { AreaLine(it.toCharArray()) }

    data class AreaLine(val areaItems: CharArray) {
        override fun equals(other: Any?): Boolean {
            other as AreaLine
            if (!areaItems.contentEquals(other.areaItems)) return false
            return true
        }

        override fun hashCode(): Int {
            return areaItems.contentHashCode()
        }

        override fun toString(): String {
            return areaItems.joinToString(separator = "")
        }
    }

    fun findOccupiedSeatsAfterSystemIsStable(): Int {
        val stableArea = iterate(
            area,
            freeSeatCondition = { it >= 4 },
            calculateOccupiedSeats = { x, y, areaLine -> calculateAdjacentOccupiedSeats(x, y, areaLine) })

        return stableArea.fold(0, { acc, s -> acc + s.areaItems.count { it == '#' } })
    }

    fun findOccupiedSeatsAfterSystemIsStableUsingDirectionMatching(): Int {
        val stableArea = iterate(
            area,
            freeSeatCondition = { it >= 5 },
            calculateOccupiedSeats = { x, y, areaLine -> calculateOccupiedSeatsInEachDirection(x, y, areaLine) })

        return stableArea.fold(0, { acc, s -> acc + s.areaItems.count { it == '#' } })
    }

    private fun iterate(
        area: List<AreaLine>,
        freeSeatCondition: (Int) -> Boolean,
        calculateOccupiedSeats: (Int, Int, List<AreaLine>) -> Int
    ): List<AreaLine> {
        val iteration = area
            .mapIndexed { x, areaLine ->
                AreaLine(areaLine.areaItems.mapIndexed { y, char ->
                    val occupiedSeats = calculateOccupiedSeats(x, y, area)
                    if (char == 'L' && occupiedSeats == 0) {
                        '#'
                    } else if (char == '#' && freeSeatCondition(occupiedSeats)) {
                        'L'
                    } else {
                        char
                    }
                }.toCharArray())
            }
        return if (area == iteration)
            iteration
        else
            iterate(iteration, freeSeatCondition, calculateOccupiedSeats)
    }

    private fun calculateAdjacentOccupiedSeats(x: Int, y: Int, area: List<AreaLine>): Int {
        return sequenceOf(
            area[x].areaItems.getAreaItem(y + 1),
            area[x].areaItems.getAreaItem(y - 1),
            area.getLine(x + 1).areaItems.getAreaItem(y),
            area.getLine(x + 1).areaItems.getAreaItem(y + 1),
            area.getLine(x + 1).areaItems.getAreaItem(y - 1),
            area.getLine(x - 1).areaItems.getAreaItem(y),
            area.getLine(x - 1).areaItems.getAreaItem(y + 1),
            area.getLine(x - 1).areaItems.getAreaItem(y - 1)
        ).count { it == '#' }
    }

    private fun calculateOccupiedSeatsInEachDirection(x: Int, y: Int, area: List<AreaLine>): Int {

        fun findFirstSeatInDirection(x: Int, y: Int, addX: Int, addY: Int): Char {
            val currentX = x + addX
            val currentY = y + addY
            val char = area.getLine(currentX).areaItems.getAreaItem(currentY)
            return if (char == '.')
                findFirstSeatInDirection(currentX, currentY, addX, addY)
            else char
        }

        return sequenceOf(
            findFirstSeatInDirection(x, y, 0, 1),
            findFirstSeatInDirection(x, y, 0, -1),
            findFirstSeatInDirection(x, y, 1, 0),
            findFirstSeatInDirection(x, y, 1, 1),
            findFirstSeatInDirection(x, y, 1, -1),
            findFirstSeatInDirection(x, y, -1, 0),
            findFirstSeatInDirection(x, y, -1, 1),
            findFirstSeatInDirection(x, y, -1, -1),
        ).count { it == '#' }
    }

    private fun CharArray.getAreaItem(index: Int): Char {
        return if (index in 0..lastIndex) get(index) else OUTSIDE_CHARACTER
    }

    private fun List<AreaLine>.getLine(index: Int): AreaLine {
        return if (index in 0..lastIndex) get(index) else AreaLine(charArrayOf())
    }
}
