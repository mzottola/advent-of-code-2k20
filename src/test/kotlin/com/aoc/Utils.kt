package com.aoc

class Utils {
    companion object {
        fun readInputLines(
            numberOfInput: String,
            filterLinesPredicate: (String) -> Boolean = { it.isNotBlank() }
        ): List<String> {
            return Utils::class.java.getResource("/input${numberOfInput}.txt")
                .readText()
                .split("\n")
                .filter(filterLinesPredicate)
        }
    }
}
