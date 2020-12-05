package com.aoc.binaryboarding

import com.aoc.Utils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardingPassFinderTest {
    @Test
    fun should_find_max_seat_id_equal_to_919() {
        val list = Utils.readInputLines("5")

        val maxSeatId = list
            .map { BoardingPassFinder(it).computeSeatId() }
            .maxOrNull()

        assertThat(maxSeatId).isEqualTo(919)
    }

    @Test
    fun should_find_missing_seat_id_equal_to_642() {
        val list = Utils.readInputLines("5")

        val seatIds = list
            .map { BoardingPassFinder(it).computeSeatId() }
            .sortedByDescending { it }

        val lastIndex = list.size - 1
        val missingSeatId = seatIds
            .filterIndexed { index, _ -> index < lastIndex }
            .filterIndexed { index, i -> i - seatIds[index + 1] > 1 }
            .map { it - 1 }
            .first()

        assertThat(missingSeatId).isEqualTo(642)
    }

}
