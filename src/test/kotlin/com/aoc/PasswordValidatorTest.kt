package com.aoc

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PasswordValidatorTest {
    @Test
    fun should_be_valid_when_contains_min_occurence() {
        val valid = PasswordValidator().isValid("1-3 a: abcde")
        Assertions.assertThat(valid).isTrue
    }

    @Test
    fun should_not_be_valid_when_contains_zero_occurence() {
        val valid = PasswordValidator().isValid("1-3 b: cdefg")
        Assertions.assertThat(valid).isFalse
    }

    @Test
    fun should_be_valid_when_contains_max_occurence() {
        val valid = PasswordValidator().isValid("2-9 c: ccccccccc")
        Assertions.assertThat(valid).isTrue
    }

    @Test
    fun should_return_the_number_of_valid_passwords() {
        val inputLines = Utils.readInputLines("2")

        val count = inputLines
            .filter { PasswordValidator().isValid(it) }
            .count()

        Assertions.assertThat(count).isEqualTo(422)
    }

    @Test
    fun should_be_valid_when_contains_one_occurence_for_new_policy() {
        val valid = PasswordValidator().isValidComparingPositions("1-3 a: abcde")
        Assertions.assertThat(valid).isTrue
    }

    @Test
    fun should_not_be_valid_when_contains_zero_occurence_for_new_policy() {
        val valid = PasswordValidator().isValidComparingPositions("1-3 b: cdefg")
        Assertions.assertThat(valid).isFalse
    }

    @Test
    fun should_be_valid_when_contains_max_occurence_for_new_policy() {
        val valid = PasswordValidator().isValidComparingPositions("2-9 c: ccccccccc")
        Assertions.assertThat(valid).isFalse
    }

    @Test
    fun should_return_the_number_of_valid_passwords_for_new_policy() {
        val inputLines = Utils.readInputLines("2")

        val count = inputLines
            .filter { PasswordValidator().isValidComparingPositions(it) }
            .count()

        Assertions.assertThat(count).isEqualTo(451)
    }
}

