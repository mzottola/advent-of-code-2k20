package com.aoc.binaryboarding

import spock.lang.Specification
import spock.lang.Unroll

import static org.assertj.core.api.Assertions.assertThat

class BoardingPassFinderSpec extends Specification {
    @Unroll
    def "should find seat ID is #expected when binarySpacePartitioning is #binarySpacePartitioning"() {
        given:
        def boardingPassFinder = new BoardingPassFinder(binarySpacePartitioning)

        when:
        def result = boardingPassFinder.computeSeatId()

        then:
        assertThat(result).isEqualTo(expected)

        where:
        binarySpacePartitioning || expected
        "BFFFBBFRRR"            || 567
        "FFFBBBFRRR"            || 119
        "BBFFBBFRLL"            || 820
    }
}
