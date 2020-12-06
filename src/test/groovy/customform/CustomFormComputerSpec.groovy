package customform

import com.aoc.customform.CustomFormComputer
import spock.lang.Specification
import spock.lang.Unroll

import static org.assertj.core.api.Assertions.assertThat

class CustomFormComputerSpec extends Specification {

    @Unroll
    def "should find #expected 'yes' given input is #input where finding anyone answered yes"() {
        given:
        def customFormComputer = new CustomFormComputer(input)

        when:
        def numberOfYes = customFormComputer.computeAnyoneAnsweredYes()

        then:
        assertThat(numberOfYes).isEqualTo(expected)

        where:
        input              || expected
        "abc"              || 3
        "a\nb\nc"          || 3
        "ab\nac"           || 3
        "a\na\na\na"       || 1
        "b"                || 1
        "abcx\nabcy\nabcz" || 6
    }

    @Unroll
    def "should find #expected 'yes' given input is #input where finding everyone answered yes"() {
        given:
        def customFormComputer = new CustomFormComputer(input)

        when:
        def numberOfYes = customFormComputer.computeEveryoneAnsweredYes()

        then:
        assertThat(numberOfYes).isEqualTo(expected)

        where:
        input              || expected
        "abc"              || 3
        "a\nb\nc"          || 0
        "ab\nac"           || 1
        "a\na\na\na"       || 1
        "b"                || 1
        "abcx\nabcy\nabcz" || 3
    }

}
