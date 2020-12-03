package com.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TobogganTrajectoryCalculatorTest {
    @Test
    fun should_count_0_tree_when_following_slope_of_right_3_and_down_1_with_2_lines() {
        val grid = listOf(
            "....",
            "...."
        )

        val countTrees = TobogganTrajectoryCalculator(grid).countTrees(3, 1)

        assertThat(countTrees).isEqualTo(0)
    }

    @Test
    fun should_count_1_tree_when_following_slope_of_right_3_and_down_1_with_2_lines() {
        val grid = listOf(
            "....",
            "...#"
        )

        val countTrees = TobogganTrajectoryCalculator(grid).countTrees(3, 1)

        assertThat(countTrees).isEqualTo(1)
    }

    @Test
    fun should_count_3_trees_when_following_slope_of_right_3_and_down_1_with_4_lines() {
        val grid = listOf(
            "....",
            "...#",
            "..#.",
            ".#.."
        )

        val countTrees = TobogganTrajectoryCalculator(grid).countTrees(3, 1)

        assertThat(countTrees).isEqualTo(3)
    }

    @Test
    fun should_count_7_trees_when_following_slope_of_right_3_and_down_1() {
        val grid = Utils.readInputLines("3-test")

        val countTrees = TobogganTrajectoryCalculator(grid).countTrees(3, 1)

        assertThat(countTrees).isEqualTo(7)
    }

    @Test
    fun should_count_171_trees_when_following_slope_of_right_3_and_down_1() {
        val grid = Utils.readInputLines("3")

        val countTrees = TobogganTrajectoryCalculator(grid).countTrees(3, 1)

        assertThat(countTrees).isEqualTo(171)
    }

    @Test
    fun should_find_result_336_when_multiplying_count_trees_for_different_slopes() {
        val grid = Utils.readInputLines("3-test")

        val result = listOf(
            TobogganTrajectoryCalculator(grid).countTrees(1, 1),
            TobogganTrajectoryCalculator(grid).countTrees(3, 1),
            TobogganTrajectoryCalculator(grid).countTrees(5, 1),
            TobogganTrajectoryCalculator(grid).countTrees(7, 1),
            TobogganTrajectoryCalculator(grid).countTrees(1, 2)
        ).reduce { acc, i -> acc * i }

        assertThat(result).isEqualTo(336)
    }

    @Test
    fun should_find_result_1206576000_when_multiplying_count_trees_for_different_slopes() {
        val grid = Utils.readInputLines("3")

        val result = listOf(
            TobogganTrajectoryCalculator(grid).countTrees(1, 1),
            TobogganTrajectoryCalculator(grid).countTrees(3, 1),
            TobogganTrajectoryCalculator(grid).countTrees(5, 1),
            TobogganTrajectoryCalculator(grid).countTrees(7, 1),
            TobogganTrajectoryCalculator(grid).countTrees(1, 2)
        ).reduce { acc, i -> acc * i }

        assertThat(result).isEqualTo(1206576000)
    }
}
