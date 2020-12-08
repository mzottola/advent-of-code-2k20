package com.aoc.handheldhalting

class BootCodeComputer(val input: List<String>) {
    data class Instruction(val function: InstructionFunction, val value: Int)

    enum class InstructionFunction(val code: String, val move: (BasicFunctionParam) -> Int) {
        NOP("nop", { it.index + 1 }),
        ACC("acc", { it.index + 1 }),
        JMP("jmp", { it.index + it.instructionValue });
    }

    data class BasicFunctionParam(
        val index: Int,
        val instructionValue: Int,
    )

    companion object {
        private val functionsByName = InstructionFunction.values().associateBy { it.code }
    }

    private val instructions = parseInstructions(input)

    fun findAccumulatorBeforeInfiniteLoop(): Int {
        val visitedIndexes = computeVisitedIndexesInOrder()
        val accumulatorInstructionsToExecute = instructions
            .filterIndexed { index, _ -> visitedIndexes.contains(index) }
            .filter { it.function == InstructionFunction.ACC }

        return accumulatorInstructionsToExecute.fold(0, { acc, instruction -> acc + instruction.value })
    }

    fun findAccumulatorWhenFixingInfiniteLoop(): Int {
        val visitedIndexes = computeVisitedIndexesInOrder()
        val newInstructions = findNewInstructionsToAvoidInfiniteLoop(visitedIndexes.toList())

        val accumulatorInstructionsToExecute = newInstructions.instructions
            .filterIndexed { index, _ -> newInstructions.visitedIndexes.contains(index) }
            .filter { it.function == InstructionFunction.ACC }

        return accumulatorInstructionsToExecute.fold(0, { acc, instruction -> acc + instruction.value })
    }

    private fun parseInstructions(input: List<String>) =
        input
            .map { it.split(" ") }
            .map { Instruction(functionsByName[it[0]]!!, it[1].toInt()) }

    private fun computeVisitedIndexesInOrder(): Set<Int> {
        val visitedIndexes = LinkedHashSet<Int>()
        var currentIndex = 0
        do {
            visitedIndexes.add(currentIndex)
            val instruction = instructions[currentIndex]
            currentIndex = instruction.function.move.invoke(BasicFunctionParam(currentIndex, instruction.value))
        } while (!visitedIndexes.contains(currentIndex))
        return visitedIndexes
    }

    private fun findNewInstructionsToAvoidInfiniteLoop(visitedIndexes: List<Int>): NewInstructions {
        val reversedVisitedIndexes = visitedIndexes.reversed()
        for (i in reversedVisitedIndexes) {
            val instruction = instructions[i]
            val isNop = instruction.function == InstructionFunction.NOP
            val isJmp = instruction.function == InstructionFunction.JMP
            if (isNop || isJmp) {
                val mutableInstructions = instructions.toMutableList()
                mutableInstructions[i] =
                    Instruction(if (isNop) InstructionFunction.JMP else InstructionFunction.NOP, instruction.value)

                val loopResult = isNotInfiniteLoop(mutableInstructions)
                if (loopResult.isNotInfiniteLoop) {
                    return NewInstructions(mutableInstructions, loopResult.visitedIndexes)
                }
            }
        }
        return NewInstructions(listOf(), listOf())
    }

    private fun isNotInfiniteLoop(instructions: List<Instruction>): LoopResult {
        val visitedIndexes = mutableSetOf(0)
        var currentIndex = 0
        for (instr in instructions) {
            val instruction = instructions[currentIndex]
            currentIndex = instruction.function.move.invoke(BasicFunctionParam(currentIndex, instruction.value))
            if (visitedIndexes.contains(currentIndex)) {
                return LoopResult(false, listOf())
            }
            if (currentIndex >= instructions.size) {
                return LoopResult(true, visitedIndexes.toList())
            }
            visitedIndexes.add(currentIndex)
        }
        return LoopResult(true, visitedIndexes.toList())
    }

    data class LoopResult(val isNotInfiniteLoop: Boolean, val visitedIndexes: List<Int>)
    data class NewInstructions(val instructions: List<Instruction>, val visitedIndexes: List<Int>)
}
