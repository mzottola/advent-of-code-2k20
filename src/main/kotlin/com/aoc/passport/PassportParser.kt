package com.aoc.passport

class PassportParser {
    companion object {
        fun parse(input: List<String>): List<String> {
            val sb = StringBuilder()
            val list = mutableListOf<String>()
            input.forEach {
                if (it.isNotEmpty()) {
                    sb.append(" ${it}")
                } else {
                    list.add(sb.toString())
                    sb.clear()
                }
            }
            return list
        }
    }
}
