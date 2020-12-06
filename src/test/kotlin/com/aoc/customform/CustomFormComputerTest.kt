package com.aoc.customform

import com.aoc.Utils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CustomFormComputerTest {
    @Test
    fun should_return_11_yes_given_input_6_test_where_anyone_answered_yes() {
        val lines = CustomFormParser.parse(Utils.readInputLines("6-test") { true })

        val result = lines
            .map { CustomFormComputer(it).computeAnyoneAnsweredYes() }
            .reduce { acc, i -> acc + i }

        Assertions.assertThat(result).isEqualTo(11)
    }

    @Test
    fun should_return_6161_yes_given_input_6_where_anyone_answered_yes() {
        val lines = CustomFormParser.parse(Utils.readInputLines("6") { true })

        val result = lines
            .map { CustomFormComputer(it).computeAnyoneAnsweredYes() }
            .reduce { acc, i -> acc + i }

        Assertions.assertThat(result).isEqualTo(6161)
    }

    @Test
    fun should_return_6_yes_given_input_6_test_where_everyone_answered_yes() {
        val lines = CustomFormParser.parse(Utils.readInputLines("6-test") { true }, true)

        val result = lines
            .map { CustomFormComputer(it).computeEveryoneAnsweredYes() }
            .reduce { acc, i -> acc + i }

        Assertions.assertThat(result).isEqualTo(6)
    }

    @Test
    fun should_return_2971_yes_given_input_6_where_everyone_answered_yes() {
        val lines = CustomFormParser.parse(Utils.readInputLines("6") { true }, true)

        val result = lines
            .map { CustomFormComputer(it).computeEveryoneAnsweredYes() }
            .reduce { acc, i -> acc + i }

        Assertions.assertThat(result).isEqualTo(2971)
    }
}
