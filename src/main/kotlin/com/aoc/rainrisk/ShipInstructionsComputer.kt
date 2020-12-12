package com.aoc.rainrisk

import kotlin.math.absoluteValue

class ShipInstructionsComputer(private val input: List<String>) {

    fun computeManhattanValue(): Int {
        val shipStable = input
            .fold(Ship('E', 0, 0), { ship, instruction -> ship.command(instruction) })
        return shipStable.x.absoluteValue + shipStable.y.absoluteValue
    }
    fun computeManhattanValueUsingRightRules(): Int {
        val shipStable = input
            .fold(ShipWithWaypoint( 0, 0), { ship, instruction -> ship.command(instruction) })
        return shipStable.x.absoluteValue + shipStable.y.absoluteValue
    }
}
