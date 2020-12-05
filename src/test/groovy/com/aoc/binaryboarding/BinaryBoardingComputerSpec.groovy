package com.aoc.binaryboarding

import spock.lang.Specification
import spock.lang.Unroll

import static org.assertj.core.api.Assertions.assertThat

class BinaryBoardingComputerSpec extends Specification {

    @Unroll
    def "should compute #expected row when path is #path"() {
        given:
        def binaryBoardingComputer = new BinaryBoardingComputer(path)

        when:
        def result = binaryBoardingComputer.findRangeRow()

        then:
        assertThat(result).containsAll(expected)

        where:
        path      || expected
        "F"       || [0, 63]
        "FB"      || [32, 63]
        "FBF"     || [32, 47]
        "FBFB"    || [40, 47]
        "FBFBB"   || [44, 47]
        "FBFBBF"  || [44, 45]
        "FBFBBFF" || [44]
    }

    @Unroll
    def "should compute #expected seat when path is #path"() {
        given:
        def binaryBoardingComputer = new BinaryBoardingComputer(path)

        when:
        def result = binaryBoardingComputer.findRangeSeat()

        then:
        assertThat(result).containsAll(expected)

        where:
        path  || expected
        "R"   || [4, 7]
        "RL"  || [4, 5]
        "RLR" || [5]
    }

}
