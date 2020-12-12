import com.aoc.SeatingSystem
import com.aoc.Utils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SeatingSystemTest {

    @Test
    fun should_find_1_occupied_seat_given_1_line_when_1_seat_exists() {
        val input = listOf("...L...")

        val result = SeatingSystem(input).findOccupiedSeatsAfterSystemIsStable()

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun should_find_3_occupied_seats_given_2_lines_when_5_seat_exists() {
        val input = listOf(
            "..LL...",
            "..LLL.."
        )

        val result = SeatingSystem(input).findOccupiedSeatsAfterSystemIsStable()

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun should_find_37_occupied_seats_with_input_test() {
        val input = Utils.readInputLines("11-test")

        val result = SeatingSystem(input).findOccupiedSeatsAfterSystemIsStable()

        assertThat(result).isEqualTo(37)
    }

    @Test
    fun should_find_2204_occupied_seats_with_input() {
        val input = Utils.readInputLines("11")

        val result = SeatingSystem(input).findOccupiedSeatsAfterSystemIsStable()

        assertThat(result).isEqualTo(2204)
    }

    @Test
    fun should_find_8_occupied_seats_in_each_direction_with_input_test_2() {
        val input = Utils.readInputLines("11-2-test")

        val result = SeatingSystem(input).findOccupiedSeatsAfterSystemIsStableUsingDirectionMatching()

        assertThat(result).isEqualTo(8)
    }

    @Test
    fun should_find_26_occupied_seats_in_each_direction_with_input_test() {
        val input = Utils.readInputLines("11-test")

        val result = SeatingSystem(input).findOccupiedSeatsAfterSystemIsStableUsingDirectionMatching()

        assertThat(result).isEqualTo(26)
    }

    @Test
    fun should_find_1986_occupied_seats_in_each_direction_with_input() {
        val input = Utils.readInputLines("11")

        val result = SeatingSystem(input).findOccupiedSeatsAfterSystemIsStableUsingDirectionMatching()

        assertThat(result).isEqualTo(1986)
    }
}
