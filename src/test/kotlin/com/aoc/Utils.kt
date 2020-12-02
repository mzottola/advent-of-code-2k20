package com.aoc

class Utils {
    companion object {
        fun readInputLines(numberOfInput: String): List<String> =
            Utils::class.java.getResource("/input${numberOfInput}.txt")
                .readText()
                .split("\n")
                .filter { it.isNotBlank() }
    }
}
