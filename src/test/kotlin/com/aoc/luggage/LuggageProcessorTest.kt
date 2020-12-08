package com.aoc.luggage

import com.aoc.Utils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class LuggageProcessorTest {

    @Test
    fun should_find_4_different_luggage_colors_containing_1_shiny_gold_luggage_with_input_test() {
        val readInputLines = Utils.readInputLines("7-test")

        val count = LuggageProcessor(readInputLines).findNumberOfLuggageContainingShinyGoldLuggage()

        Assertions.assertThat(count).isEqualTo(4)
    }

    @Test
    fun should_find_224_different_luggage_colors_containing_1_shiny_gold_luggage_with_input() {
        val readInputLines = Utils.readInputLines("7")

        val count = LuggageProcessor(readInputLines).findNumberOfLuggageContainingShinyGoldLuggage()

        Assertions.assertThat(count).isEqualTo(224)
    }

    @Test
    fun should_find_6_individual_bags_in_1_shiny_gold_bag_with_only_one_level() {
        val readInputLines = listOf(
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain no other bags.",
            "vibrant plum bags contain no other bags."
        )

        val count = LuggageProcessor(readInputLines).findIndividualLuggageForAShinyGoldLuggage()

        Assertions.assertThat(count).isEqualTo(6)
    }

    @Test
    fun should_find_12_individual_bags_in_1_shiny_gold_bag_with_two_levels() {
        val readInputLines = listOf(
            "shiny gold bags contain 1 dark olive bags, 2 vibrant plum bags.",
            "dark olive bags contain 1 faded blue bags, 2 dotted black bags.",
            "vibrant plum bags contain 3 faded blue bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
        )

        val count = LuggageProcessor(readInputLines).findIndividualLuggageForAShinyGoldLuggage()

        Assertions.assertThat(count).isEqualTo(12)
    }

    @Test
    fun should_find_32_individual_bags_in_1_shiny_gold_bag_with_input_test() {
        val readInputLines = Utils.readInputLines("7-test")

        val count = LuggageProcessor(readInputLines).findIndividualLuggageForAShinyGoldLuggage()

        Assertions.assertThat(count).isEqualTo(32)
    }

    @Test
    fun should_find_126_individual_bags_in_1_shiny_gold_bag_with_input_test_2() {
        val readInputLines = Utils.readInputLines("7-2-test")

        val count = LuggageProcessor(readInputLines).findIndividualLuggageForAShinyGoldLuggage()

        Assertions.assertThat(count).isEqualTo(126)
    }

    @Test
    fun should_find_XX_individual_bags_in_1_shiny_gold_bag_with_input() {
        val readInputLines = Utils.readInputLines("7")

        val count = LuggageProcessor(readInputLines).findIndividualLuggageForAShinyGoldLuggage()

        Assertions.assertThat(count).isEqualTo(0)
    }

}
