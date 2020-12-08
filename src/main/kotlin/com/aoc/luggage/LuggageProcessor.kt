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
        val shinyGoldDirectChildren = getLuggageChildren("shiny gold")
        val directChildrenLuggageQuantity = shinyGoldDirectChildren
            .map { it.number }
            .reduce { acc, i -> acc + i }
        val everyChildrenLuggageQuantity = shinyGoldDirectChildren
            .map { findAllCapacity(it) }
            .reduce { acc, i -> acc + i }
        return everyChildrenLuggageQuantity + directChildrenLuggageQuantity
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

    private fun findAllCapacity(luggage: Luggage): Int {
        val children = getLuggageChildren(luggage.name)
        if (children.isEmpty()) {
            return 1
        }
        val allChildrenCapacity = children
            .map { it.number.times(findAllCapacity(it)) }
            .reduce { acc, i -> acc + i }
        return luggage.number.times(allChildrenCapacity)
    }

    private fun getLuggageChildren(name: String): List<Luggage> {
        return childrenByLuggage[name]!!
    }

}
