package com.aoc.passport

class PassportValidator(val passportAsString: String) {
    companion object {
        val validationMap = Validation.values().associateBy { it.code }
    }

    fun isValid(): Boolean {
        return createPassportMap()
            .keys
            .containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))
    }

    fun isValidUsingValidationRules(): Boolean {
        return createPassportMap()
            .filter { validationMap[it.key]!!.rule.invoke(it.value) }
            .keys
            .containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))
    }

    private fun createPassportMap() =
        passportAsString
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.split(":") }
            .map { Pair(it[0], it[1]) }
            .associateBy({ it.first }, { it.second })

    enum class Validation(val code: String, val rule: (String) -> Boolean) {
        BYR("byr", { year -> year.toInt() in 1920..2002 }),
        IYR("iyr", { year -> year.toInt() in 2010..2020 }),
        EYR("eyr", { year -> year.toInt() in 2020..2030 }),
        HGT("hgt", { hgt -> validateHgt(hgt) }),
        HCL("hcl", { hcl -> "^#([0-9-a-f]{6})$".toRegex().matches(hcl) }),
        ECL("ecl", { ecl -> eclValues.contains(ecl) }),
        PID("pid", { pid -> "[0-9]{9}".toRegex().matches(pid) }),
        CID("cid", { true });

        companion object {
            val eclValues = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            fun validateHgt(hgt: String): Boolean {
                return (hgtIsValidByMeasureOperator(hgt, "cm") { it in 150..193 }
                        || hgtIsValidByMeasureOperator(hgt, "in") { it in 59..76 })
            }

            private fun hgtIsValidByMeasureOperator(
                hgt: String,
                operator: String,
                rangePredicate: (Int?) -> Boolean
            ): Boolean {
                if (hgt.contains(operator)) {
                    if (hgt
                            .replace(operator, "")
                            .toIntOrNull()
                            .takeIf { rangePredicate.invoke(it) } != null
                    ) {
                        return true
                    }
                }
                return false
            }
        }
    }
}

