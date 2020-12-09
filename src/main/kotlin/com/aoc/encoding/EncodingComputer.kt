package com.aoc.encoding

class EncodingComputer(input: List<String>, private val offset: Int = 0) {
    private val xmasNumbers = input.map { it.toLong() }

    fun findFirstError(): Long {
        return xmasNumbers
            .filterIndexed { index, number -> index < offset || isNotValid(index, number) }[offset]
    }

    fun findEncryptionWeakness(sumToFind: Long): Long {
        return findContiguousList(sumToFind).sorted().let { it.first() + it.last() }
    }

    private fun isNotValid(index: Int, number: Long): Boolean {
        val subList = xmasNumbers.subList(index - offset, index).filterNot { it * 2 == number }
        val difference = subList.map { number - it }
        return subList.intersect(difference).isEmpty()
    }

    private fun findContiguousList(sumToFind: Long): List<Long> {
        var start = 0
        var limit = 1
        do {
            val subList = xmasNumbers.subList(start, limit)
            val sum = subList.reduce { acc, l -> acc + l }
            when {
                sum == sumToFind -> {
                    return subList
                }
                sum < sumToFind -> {
                    limit += 1
                }
                sum > sumToFind -> {
                    start += 1
                    limit = start + 1
                }
            }
        } while (start != xmasNumbers.size - 1)
        return listOf()
    }
}
