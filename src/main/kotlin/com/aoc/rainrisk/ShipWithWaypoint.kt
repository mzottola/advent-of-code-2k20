package com.aoc.rainrisk

import kotlin.math.roundToInt

class ShipWithWaypoint(
    val x: Int,
    val y: Int,
    val waypoint: Waypoint = Waypoint(10, 1)
) {
    data class Waypoint(val x: Int, val y: Int)

    companion object {
        private val instructionsByCharacter = mapOf<Char, (ShipWithWaypoint, Int) -> ShipWithWaypoint>(
            Pair('N') { ship, moveY -> ship.moveWaypoint(ship.waypoint.x, ship.waypoint.y + moveY) },
            Pair('S') { ship, moveY -> ship.moveWaypoint(ship.waypoint.x, ship.waypoint.y - moveY) },
            Pair('E') { ship, moveX -> ship.moveWaypoint(ship.waypoint.x + moveX, ship.waypoint.y) },
            Pair('W') { ship, moveX -> ship.moveWaypoint(ship.waypoint.x - moveX, ship.waypoint.y) },
            Pair('L') { ship, degrees -> ship.rotateWaypoint(-degrees) },
            Pair('R') { ship, degrees -> ship.rotateWaypoint(degrees) },
        )
    }

    fun command(instruction: String): ShipWithWaypoint {
        val partition = instruction.partition { it.isLetter() }
        return if (partition.first == "F") {
            moveShip(partition.second.toInt())
        } else {
            instructionsByCharacter.getValue(partition.first.first()).invoke(this, partition.second.toInt())
        }
    }

    private fun moveWaypoint(newX: Int, newY: Int): ShipWithWaypoint {
        return ShipWithWaypoint(x, y, Waypoint(newX, newY))
    }

    private fun rotateWaypoint(degrees: Int): ShipWithWaypoint {
        val angle = degrees * Math.PI / 180
        val waypointX = (waypoint.x * Math.cos(angle) + waypoint.y * Math.sin(angle)).roundToInt()
        val waypointY = (-waypoint.x * Math.sin(angle) + waypoint.y * Math.cos(angle)).roundToInt()

        return ShipWithWaypoint(x, y, Waypoint(waypointX, waypointY))
    }

    private fun moveShip(iterations: Int): ShipWithWaypoint {
        val xToAdd = waypoint.x * iterations
        val yToAdd = waypoint.y * iterations
        return ShipWithWaypoint( x + xToAdd, y + yToAdd, waypoint)
    }

    override fun toString(): String {
        return "[$x;$y]-[${waypoint.x};${waypoint.y}]"
    }
}


