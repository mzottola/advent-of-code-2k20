package com.aoc.luggage

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LuggageParserTest {

    @Test
    fun should_find_1_luggage_with_no_child() {
        val input = listOf("dotted black bags contain no other bags.")

        val childrenByLuggage = LuggageParser(input).createAllLuggage()

        assertThat(childrenByLuggage).isEqualTo(
            mapOf(
                Pair("dotted black", emptyList<Luggage>())
            )
        )
    }

    @Test
    fun should_find_2_luggage_which_can_contain_each_2_luggage() {
        val input = listOf(
            "bright white bags contain 1 shiny gold bag, 2 shiny green bags.",
            "bright blue bags contain 1 shiny brown bag, 2 shiny red bags."
        )

        val childrenByLuggage = LuggageParser(input).createAllLuggage()

        assertThat(childrenByLuggage).isEqualTo(
            mapOf(
                Pair("bright white", listOf(Luggage("shiny gold", 1), Luggage("shiny green", 2))),
                Pair("bright blue", listOf(Luggage("shiny brown", 1), Luggage("shiny red", 2)))
            )
        )
    }
}
