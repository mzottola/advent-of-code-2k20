package com.aoc.adapterarray

import com.aoc.Utils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JoltageAdapterComputerTest {

    @Test
    fun should_find_7_diff_of_1_and_5_diff_of_3_with_input_test() {
        val input = Utils.readInputLines("10-test")

        val differences = JoltageAdapterComputer(input).findDifferences()

        assertThat(differences.d1).isEqualTo(7)
        assertThat(differences.d2).isEqualTo(0)
        assertThat(differences.d3).isEqualTo(5)
    }

    @Test
    fun should_find_22_diff_of_1_and_10_diff_of_3_with_input_test() {
        val input = Utils.readInputLines("10-2-test")

        val differences = JoltageAdapterComputer(input).findDifferences()

        assertThat(differences.d1).isEqualTo(22)
        assertThat(differences.d2).isEqualTo(0)
        assertThat(differences.d3).isEqualTo(10)
    }

    @Test
    fun should_find_71_diff_of_1_and_29_diff_of_3_with_input_test() {
        val input = Utils.readInputLines("10")

        val differences = JoltageAdapterComputer(input).findDifferences()

        assertThat(differences.d1).isEqualTo(71)
        assertThat(differences.d2).isEqualTo(0)
        assertThat(differences.d3).isEqualTo(29)
        println(71 * 29)
    }

    @Test
    fun should_find_8_max_distinct_ways_with_input_test() {
        val input = Utils.readInputLines("10-test")

        val maxDistinctWays = JoltageAdapterComputer(input).findMaxDistinctWays()

        assertThat(maxDistinctWays).isEqualTo(8)
    }

    @Test
    fun should_find_19208_max_distinct_ways_with_input_test_2() {
        val input = Utils.readInputLines("10-2-test")

        val maxDistinctWays = JoltageAdapterComputer(input).findMaxDistinctWays()

        assertThat(maxDistinctWays).isEqualTo(19208)
    }

    @Test
    fun should_find_86812553324672_max_distinct_ways_with_input_test() {
        val input = Utils.readInputLines("10")

        val maxDistinctWays = JoltageAdapterComputer(input).findMaxDistinctWays()

        assertThat(maxDistinctWays).isEqualTo(86812553324672)
    }
}
