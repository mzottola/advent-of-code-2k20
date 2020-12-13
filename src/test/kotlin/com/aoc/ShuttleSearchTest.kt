package com.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ShuttleSearchTest {

    @Test
    fun should_find_result_is_59_with_input_test() {
        val input = Utils.readInputLines("13-test")

        val result = ShuttleSearch(input).findResultForFirstBusMatch()

        assertThat(result).isEqualTo(295)
    }

    @Test
    fun should_find_result_is_5257_with_input() {
        val input = Utils.readInputLines("13")

        val result = ShuttleSearch(input).findResultForFirstBusMatch()

        assertThat(result).isEqualTo(5257)
    }

    @Test
    fun should_find_timestamp_is_3417_where_buses_depart_at_offset() {
        val input = listOf("1", "17,x,13,19")

        val timestamp = ShuttleSearch(input).findTimestampForBusesDepartMatchingOffsets()

        assertThat(timestamp).isEqualTo(3417)
    }

    @Test
    fun should_find_timestamp_is_1068788_where_buses_depart_at_offset_with_input_test() {
        val input = Utils.readInputLines("13-test")

        val timestamp = ShuttleSearch(input).findTimestampForBusesDepartMatchingOffsets()

        assertThat(timestamp).isEqualTo(1068781L)
    }

    @Test
    fun should_find_timestamp_is_X_where_buses_depart_at_offset_with_input() {
        val input = Utils.readInputLines("13")

        val timestamp = ShuttleSearch(input).findTimestampForBusesDepartMatchingOffsets()

        assertThat(timestamp).isEqualTo(0)
    }
}
