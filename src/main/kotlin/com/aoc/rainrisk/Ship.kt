package com.aoc.rainrisk

class Ship(private val facing: Char, val x: Int, val y: Int) {
    companion object {
        private val directionsRing = DirectionsRing()
        private val instructionsByCharacter = mapOf<Char, (Ship, Int) -> Ship>(
            Pair('N') { ship, moveY -> Ship(ship.facing, ship.x, ship.y + moveY) },
            Pair('S') { ship, moveY -> Ship(ship.facing, ship.x, ship.y - moveY) },
            Pair('E') { ship, moveX -> Ship(ship.facing, ship.x + moveX, ship.y) },
            Pair('W') { ship, moveX -> Ship(ship.facing, ship.x - moveX, ship.y) },
            Pair('L') { ship, degrees -> Ship(directionsRing.turn(ship.facing, -degrees), ship.x, ship.y) },
            Pair('R') { ship, degrees -> Ship(directionsRing.turn(ship.facing, degrees), ship.x, ship.y) },
        )

        class DirectionsRing {
            private val directions = listOf('N', 'E', 'S', 'W')
            private val indexByDirection = mapOf(
                Pair('N', 0),
                Pair('E', 1),
                Pair('S', 2),
                Pair('W', 3),
            )

            fun turn(initialDirection: Char, degrees: Int): Char {
                val index = indexByDirection.getValue(initialDirection)
                val offset = degrees / 90
                val newIndex = index + offset
                return when {
                    newIndex > directions.indices.last -> {
                        directions[newIndex % directions.size]
                    }
                    newIndex < 0 -> {
                        directions[directions.size + newIndex]
                    }
                    else -> {
                        directions[newIndex]
                    }
                }
            }

        }

    }

    fun command(instruction: String): Ship {
        val partition = instruction.partition { it.isLetter() }
        return if (partition.first == "F") {
            instructionsByCharacter.getValue(this.facing).invoke(this, partition.second.toInt())
        } else {
            instructionsByCharacter.getValue(partition.first.first()).invoke(this, partition.second.toInt())
        }
    }

    override fun toString(): String {
        return "[$facing;$x;$y]"
    }
}
