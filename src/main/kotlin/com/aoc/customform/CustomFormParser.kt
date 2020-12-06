package com.aoc.customform

class CustomFormParser {
    companion object {
        fun parse(input: List<String>, keepGroupSeparation: Boolean = false): List<String> {
            val sb = StringBuilder()
            val list = mutableListOf<String>()
            input.forEach {
                if (it.isNotEmpty()) {
                    if (keepGroupSeparation && sb.isNotEmpty()) {
                        sb.append("\n$it")
                    } else {
                        sb.append(it)
                    }
                } else {
                    list.add(sb.toString())
                    sb.clear()
                }
            }
            return list
        }
    }
}
