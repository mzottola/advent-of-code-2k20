package com.aoc

class SumComputer {

    fun find2NumbersThatSumUp2k20(numbers: Set<Int>) = numbers.intersect(numbers.map { 2020 - it })

    fun find3NumbersThatSumUp2k20(numbers: Set<Int>): Set<Int> {
        numbers.forEach {
            val missing2Numbers = findMissingNumberInSet(it, numbers)
            if (missing2Numbers.isNotEmpty()) {
                return setOf(it, *missing2Numbers.first())
            }
        }
        return setOf()
    }

    private fun findMissingNumberInSet(number: Int, numbers: Set<Int>) =
        numbers
            .map { arrayOf(2020 - (number + it), it) }
            .filter { numbers.contains(it.first()) }
}
