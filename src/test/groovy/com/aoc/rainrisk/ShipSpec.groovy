package com.aoc.rainrisk

import org.assertj.core.api.Assertions
import spock.lang.Specification
import spock.lang.Unroll

class ShipSpec extends Specification {

    @Unroll
    def "should expect ship=#expected when the move direction #instruction is executed"() {
        given:
        def ship = new Ship('E' as char, 0, 0)

        when:
        def newShip = ship.command(instruction)

        then:
        Assertions.assertThat(newShip.toString()).isEqualTo(expected.toString())

        where:
        instruction || expected
        "N10"       || new Ship('E' as char, 0, 10)
        "S10"       || new Ship('E' as char, 0, -10)
        "E10"       || new Ship('E' as char, 10, 0)
        "W10"       || new Ship('E' as char, -10, 0)
        "F10"       || new Ship('E' as char, 10, 0)
    }

    @Unroll
    def "should expect ship=#expected when the turn direction #instruction is executed"() {
        given:
        def ship = new Ship('E' as char, 0, 0)

        when:
        def newShip = ship.command(instruction)

        then:
        Assertions.assertThat(newShip.toString()).isEqualTo(expected.toString())

        where:
        instruction || expected
        "L90"       || new Ship('N' as char, 0, 0)
        "L180"      || new Ship('W' as char, 0, 0)
        "L270"      || new Ship('S' as char, 0, 0)
        "R90"       || new Ship('S' as char, 0, 0)
        "R180"      || new Ship('W' as char, 0, 0)
        "R270"      || new Ship('N' as char, 0, 0)
    }

}
