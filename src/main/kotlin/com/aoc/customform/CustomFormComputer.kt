package com.aoc.customform

class CustomFormComputer(private val input: String) {
    fun computeAnyoneAnsweredYes() =
        input.split("\n")
            .reduce { acc, s -> acc + s }
            .toCharArray()
            .distinctBy { it }
            .size

    fun computeEveryoneAnsweredYes() =
        input.split("\n")
            .map { it.toCharArray().toSet() }
            .reduce { acc, set -> acc.intersect(set) }
            .size


}
