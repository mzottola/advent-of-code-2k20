package com.aoc

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SumComputerTest {

    @Test
    fun should_find_2_numbers_that_sum_to_2020_for_a_minimal_set() {
        val numbers = setOf(1721, 979, 366, 299, 675, 1456)
        val foundNumbers = SumComputer().find2NumbersThatSumUp2k20(numbers)
        Assertions.assertThat(foundNumbers).isEqualTo(setOf(1721, 299))
    }

    @Test
    fun should_find_2_numbers_that_sum_to_2020_for_input_1_1() {
        val numbers = SumComputerTest::class.java.getResource("/input1.txt")
            .readText()
            .split("\n")
            .filter { it.isNotBlank() }
            .map(String::toInt)
            .toSet()

        val foundNumbers = SumComputer().find2NumbersThatSumUp2k20(numbers)
        Assertions.assertThat(foundNumbers).isEqualTo(setOf(1084, 936))
    }

    @Test
    fun should_find_3_numbers_that_sum_to_2020_for_a_minimal_set() {
        val numbers = setOf(1721, 979, 299, 366, 1456, 675)
        val foundNumbers = SumComputer().find3NumbersThatSumUp2k20(numbers)
        Assertions.assertThat(foundNumbers).isEqualTo(setOf(979, 366, 675))
    }

    @Test
    fun should_find_3_numbers_that_sum_to_2020_for_input_1_1() {
        val numbers = SumComputerTest::class.java.getResource("/input1.txt")
            .readText()
            .split("\n")
            .filter { it.isNotBlank() }
            .map(String::toInt)
            .toSet()

        val foundNumbers = SumComputer().find3NumbersThatSumUp2k20(numbers)
        Assertions.assertThat(foundNumbers).isEqualTo(setOf(704, 1223, 93))
    }
}
