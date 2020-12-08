package com.aoc.handheldhalting

import com.aoc.Utils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class BootCodeComputerTest {

    @Test
    fun should_find_5_in_accumulator_before_infinite_loop_with_input_test() {
        val readInputLines = Utils.readInputLines("8-test")

        val acc = BootCodeComputer(readInputLines).findAccumulatorBeforeInfiniteLoop()

        Assertions.assertThat(acc).isEqualTo(5)
    }

    @Test
    fun should_find_1814_in_accumulator_before_infinite_loop_with_input() {
        val readInputLines = Utils.readInputLines("8")

        val acc = BootCodeComputer(readInputLines).findAccumulatorBeforeInfiniteLoop()

        Assertions.assertThat(acc).isEqualTo(1814)
    }

    @Test
    fun should_find_8_in_accumulator_when_fixing_infinite_loop_with_input_test() {
        val readInputLines = Utils.readInputLines("8-test")

        val acc = BootCodeComputer(readInputLines).findAccumulatorWhenFixingInfiniteLoop()

        Assertions.assertThat(acc).isEqualTo(8)
    }

    @Test
    fun should_find_1056_in_accumulator_when_fixing_infinite_loop_with_input_test() {
        val readInputLines = Utils.readInputLines("8")

        val acc = BootCodeComputer(readInputLines).findAccumulatorWhenFixingInfiniteLoop()

        Assertions.assertThat(acc).isEqualTo(1056)
    }
}
