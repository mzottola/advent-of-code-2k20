package com.aoc.passport

import com.aoc.Utils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PassportValidatorTest {

    @Test
    fun should_valid_2_passports() {
        val list = PassportParser.parse(Utils.readInputLines("4-test") { true })

        val numberOfValidPassports = list.count { PassportValidator(it).isValid() }

        assertThat(numberOfValidPassports).isEqualTo(2)
    }

    @Test
    fun should_valid_226_passports() {
        val list = PassportParser.parse(Utils.readInputLines("4") { true })

        // when
        val numberOfValidPassports = list.count { PassportValidator(it).isValid() }

        // then
        assertThat(numberOfValidPassports).isEqualTo(226)
    }

    @Test
    fun should_validate_byr() {
        listOf(
            Pair("1920", true),
            Pair("2002", true),
            Pair("1960", true),
            Pair("2003", false),
            Pair("1919", false),
        ).forEach {
            assertThat(PassportValidator.Validation.BYR.rule.invoke(it.first)).`as`("for byr ${it.first}")
                .isEqualTo(it.second)
        }
    }

    @Test
    fun should_validate_iyr() {
        listOf(
            Pair("2010", true),
            Pair("2020", true),
            Pair("2015", true),
            Pair("2009", false),
            Pair("2021", false),
        ).forEach {
            assertThat(PassportValidator.Validation.IYR.rule.invoke(it.first)).`as`("for iyr ${it.first}")
                .isEqualTo(it.second)
        }
    }

    @Test
    fun should_validate_eyr() {
        listOf(
            Pair("2020", true),
            Pair("2030", true),
            Pair("2025", true),
            Pair("2019", false),
            Pair("2031", false),
        ).forEach {
            assertThat(PassportValidator.Validation.EYR.rule.invoke(it.first)).`as`("for eyr ${it.first}")
                .isEqualTo(it.second)
        }
    }

    @Test
    fun should_validate_hgt() {
        listOf(
            Pair("150cm", true),
            Pair("193cm", true),
            Pair("160cm", true),
            Pair("59in", true),
            Pair("76in", true),
            Pair("65in", true),
            Pair("149cm", false),
            Pair("194cm", false),
            Pair("58in", false),
            Pair("77in", false),
            Pair("cm", false),
            Pair("in", false),
            Pair("19", false),
            Pair("", false)
        ).forEach {
            assertThat(PassportValidator.Validation.HGT.rule.invoke(it.first)).`as`("for hgt ${it.first}")
                .isEqualTo(it.second)
        }
    }

    @Test
    fun should_validate_hcl() {
        listOf(
            Pair("#123456", true),
            Pair("#abcdef", true),
            Pair("123456", false),
            Pair("abcdef", false),
            Pair("#1234567", false),
            Pair("#12345", false),
            Pair("#abcdefg", false),
            Pair("#abcde", false),
            Pair("#", false),
            Pair("", false),
        ).forEach {
            assertThat(PassportValidator.Validation.HCL.rule.invoke(it.first)).`as`("for hcl ${it.first}")
                .isEqualTo(it.second)
        }
    }

    @Test
    fun should_validate_ecl() {
        listOf(
            Pair("amb", true),
            Pair("blu", true),
            Pair("brn", true),
            Pair("gry", true),
            Pair("grn", true),
            Pair("hzl", true),
            Pair("oth", true),
            Pair("foo", false),
            Pair("", false),
        ).forEach {
            assertThat(PassportValidator.Validation.ECL.rule.invoke(it.first)).`as`("for ecl ${it.first}")
                .isEqualTo(it.second)
        }
    }

    @Test
    fun should_validate_pid() {
        listOf(
            Pair("000000001", true),
            Pair("0123456789", false),
        ).forEach {
            assertThat(PassportValidator.Validation.PID.rule.invoke(it.first)).`as`("for pid ${it.first}")
                .isEqualTo(it.second)
        }
    }

    @Test
    fun should_valid_4_passports_using_rules() {
        val list = PassportParser.parse(Utils.readInputLines("4-test-valid-passports") { true })

        val numberOfValidPassports = list.count { PassportValidator(it).isValidUsingValidationRules() }

        assertThat(numberOfValidPassports).isEqualTo(4)
    }

    @Test
    fun should_valid_0_passport_using_rules() {
        val list = PassportParser.parse(Utils.readInputLines("4-test-invalid-passports") { true })

        val numberOfValidPassports = list.count { PassportValidator(it).isValidUsingValidationRules() }

        assertThat(numberOfValidPassports).isZero();
    }

    @Test
    fun should_valid_160_passports_using_rules() {
        val list = PassportParser.parse(Utils.readInputLines("4") { true })

        val numberOfValidPassports = list.count { PassportValidator(it).isValidUsingValidationRules() }

        assertThat(numberOfValidPassports).isEqualTo(160)
    }
}
