package com.aoc.encoding

import com.aoc.Utils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EncodingComputerTest {

    @Test
    fun should_find_first_error_is_127_with_input_test() {
        val input = Utils.readInputLines("9-test")

        val firstError = EncodingComputer(input, 5).findFirstError()

        assertThat(firstError).isEqualTo(127)
    }

    @Test
    fun should_find_first_error_is_530627549_with_input() {
        val input = Utils.readInputLines("9")

        val firstError = EncodingComputer(input, 25).findFirstError()

        assertThat(firstError).isEqualTo(530627549L)
    }

    @Test
    fun should_find_encryption_weakness_equal_to_62_with_input_test() {
        val input = Utils.readInputLines("9-test")

        val firstError = EncodingComputer(input).findEncryptionWeakness(127L)

        assertThat(firstError).isEqualTo(62)
    }

    @Test
    fun should_find_encryption_weakness_equal_to_77730285_with_input() {
        val input = Utils.readInputLines("9")

        val firstError = EncodingComputer(input).findEncryptionWeakness(530627549L)

        assertThat(firstError).isEqualTo(77730285L)
    }
}
