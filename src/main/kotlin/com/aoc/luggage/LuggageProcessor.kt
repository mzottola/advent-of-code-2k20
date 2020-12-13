package com.aoc.luggage

class LuggageProcessor(private val input: List<String>) {
    private val childrenByLuggage = LuggageParser(input).createAllLuggage()

    fun findNumberOfLuggageContainingShinyGoldLuggage(): Int {
        return childrenByLuggage
            .filter { entry ->
                entry.value.map { it.name }.contains("shiny gold")
                        || canChildrenContainShinyGoldLuggage(getLuggageChildren(entry.key))
            }
            .count()
    }

    fun findIndividualLuggageForAShinyGoldLuggage(): Int {
        val shinyGoldDirectChildren = childrenByLuggage.getValue("shiny gold")

        fun recursive(luggages: List<Luggage>): Int {
            return luggages
                .map {
                    val children = childrenByLuggage.getValue(it.name)
                    if (children.isEmpty()) {
                        it.number
                    } else {
                        it.number + it.number * recursive(children)
                    }
                }
                .sum()
        }

        return recursive(shinyGoldDirectChildren)
    }

    private fun canChildrenContainShinyGoldLuggage(children: List<Luggage>): Boolean {
        if (children.isEmpty())
            return false
        return if (children.map { it.name }.contains("shiny gold"))
            true
        else
            children
                .map { getLuggageChildren(it.name) }
                .map { canChildrenContainShinyGoldLuggage(it) }
                .contains(true)
    }

    private fun getLuggageChildren(name: String): List<Luggage> {
        return childrenByLuggage[name]!!
    }

}
