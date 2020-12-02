package com.aoc

class PasswordValidator {

    fun isValid(inputString: String): Boolean {
        val input = createInput(inputString)
        val count = input.password.count { it == input.rule.letter }
        return count >= input.rule.min && count <= input.rule.max
    }

    fun isValidComparingPositions(inputString: String): Boolean {
        val input = createInput(inputString)
        val containsLetterAtFirstPosition = input.password[input.rule.min - 1] == input.rule.letter
        val containsLetterAtSecondPosition = input.password[input.rule.max - 1] == input.rule.letter
        return if (containsLetterAtFirstPosition and containsLetterAtSecondPosition) {
            false
        } else {
            containsLetterAtFirstPosition or containsLetterAtSecondPosition
        }
    }

    data class PasswordRule(val min: Int, val max: Int, val letter: Char)
    data class Input(val password: String, val rule: PasswordRule)

    private fun createInput(line: String): Input {
        return line.split(":").let {
            Input(it[1].substring(1), createPasswordRule(it[0]))
        }
    }

    private fun createPasswordRule(rule: String): PasswordRule {
        return rule.split(" ").let {
            val minMaxArray = it[0].split("-")
            PasswordRule(minMaxArray[0].toInt(), minMaxArray[1].toInt(), it[1].single())
        }
    }
}
