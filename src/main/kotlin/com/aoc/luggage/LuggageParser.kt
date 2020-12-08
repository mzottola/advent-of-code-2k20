package com.aoc.luggage

class LuggageParser(private val input: List<String>) {

    fun createAllLuggage(): Map<String, List<Luggage>> {
        val luggage = input
            .map { it.split(" ") }
            .map { "${it[0]} ${it[1]}" }

        val children = input
            .map { it.split(" ", limit = 5).last() }
            .map { it.split(",") }
            .map { it.map { s -> s.trim() } }
            .map { createChildren(it) }

        return luggage
            .zip(children)
            .associate { it }
    }

    private fun createChildren(children: List<String>): List<Luggage> {
        if (children.size == 1 && children.contains("no other bags.")) {
            return listOf()
        }
        return children.map {
            val split = it.split(" ")
            Luggage("${split[1]} ${split[2]}", split[0].toInt())
        }
    }

}
