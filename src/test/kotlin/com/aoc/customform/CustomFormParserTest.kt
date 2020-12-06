package com.aoc.customform

import com.aoc.Utils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CustomFormParserTest {

    @Test
    fun should_group_forms_by_removing_the_separated_character() {
        val lines = Utils.readInputLines("6-test") { true }

        val result = CustomFormParser.parse(lines)

        Assertions.assertThat(result).isEqualTo(
            listOf(
                "abc",
                "abc",
                "abac",
                "aaaa",
                "b"
            )
        )
    }

    @Test
    fun should_group_forms_by_keeping_the_separated_character() {
        val lines = Utils.readInputLines("6-test") { true }

        val result = CustomFormParser.parse(lines, true)

        Assertions.assertThat(result).isEqualTo(
            listOf(
                "abc",
                "a\nb\nc",
                "ab\nac",
                "a\na\na\na",
                "b"
            )
        )
    }
}
