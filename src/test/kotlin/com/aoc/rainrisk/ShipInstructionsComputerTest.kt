package com.aoc.rainrisk

import com.aoc.Utils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class ShipInstructionsComputerTest {

    @Test
    fun should_find_manhattan_value_equal_to_25_with_input_test() {
        val input = Utils.readInputLines("12-test")

        val manhattanValue = ShipInstructionsComputer(input).computeManhattanValue()

        Assertions.assertThat(manhattanValue).isEqualTo(25)
    }

    @Test
    fun should_find_manhattan_value_equal_to_757_with_input() {
        val input = Utils.readInputLines("12")

        val manhattanValue = ShipInstructionsComputer(input).computeManhattanValue()

        Assertions.assertThat(manhattanValue).isEqualTo(757)
    }

    @Test
    fun should_find_manhattan_value_equal_to_286_using_right_rules_with_input_test() {
        val input = Utils.readInputLines("12-test")

        val manhattanValue = ShipInstructionsComputer(input).computeManhattanValueUsingRightRules()

        Assertions.assertThat(manhattanValue).isEqualTo(286)
    }

    @Test
    fun should_find_manhattan_value_equal_to_51249_using_right_rules_with_input() {
        val input = Utils.readInputLines("12")

        val manhattanValue = ShipInstructionsComputer(input).computeManhattanValueUsingRightRules()

        Assertions.assertThat(manhattanValue).isEqualTo(51249)
    }
}
