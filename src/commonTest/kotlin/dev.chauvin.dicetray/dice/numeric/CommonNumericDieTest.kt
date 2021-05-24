package dev.chauvin.dicetray.dice.numeric

import dev.chauvin.dicetray.roll.numeric.NumericRollModifier
import dev.chauvin.dicetray.roll.numeric.NumericRollResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class CommonNumericDieTest {
    @Test
    fun test_shortcut_d4_faces() {
        val die = NumericDie.d4()

        assertEquals(
            listOf(1, 2, 3, 4),
            die.faces
        )
    }

    @Test
    fun test_shortcut_d6_faces() {
        val die = NumericDie.d6()

        assertEquals(
            listOf(1, 2, 3, 4, 5, 6),
            die.faces
        )
    }

    @Test
    fun test_shortcut_d8_faces() {
        val die = NumericDie.d8()

        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8),
            die.faces
        )
    }

    @Test
    fun test_shortcut_d10_faces() {
        val die = NumericDie.d10()

        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            die.faces
        )
    }

    @Test
    fun test_shortcut_d12_faces() {
        val die = NumericDie.d12()

        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
            die.faces
        )
    }

    @Test
    fun test_shortcut_d20_faces() {
        val die = NumericDie.d20()

        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
            die.faces
        )
    }

    @Test
    fun test_shortcut_d100_faces() {
        val die = NumericDie.d100()

        assertEquals(
            (1..100).toList(),
            die.faces
        )
    }

    @Test
    fun lower_bound_cannot_be_greater_than_upper_bound() {
        /**
         * Trying to initialize a Die with a lower bound greater than
         * the upper bound should throw an error.
         */

        assertFailsWith<IllegalArgumentException> {
            NumericDie.withBounds(lowerBound = 3, upperBound = 2)
        }
    }

    @Test
    fun roll_function_returns_roll_result_object() {
        val die = NumericDie.withBounds(1, 6)

        assertIs<NumericRollResult>(die.roll())
    }

    @Test
    fun roll_result_is_sum_of_roll_and_modifiers() {
        val modifiers = listOf(
            NumericRollModifier(-3),
            NumericRollModifier(4),
            NumericRollModifier(5)
        )
        val die = NumericDie.withBounds(1, 6, modifiers)
        val roll = die.roll()

        assertEquals(roll.value, roll.rawValue + roll.modifiers.sumOf {it.value})
    }

    @Test
    fun roll_multiple_returns_a_list_of_roll_result_objects() {
        val die = NumericDie.withBounds(1, 6)

        assertIs<List<NumericRollResult>>(die.rollMultiple(4))
    }

    @Test
    fun roll_multiple_returns_a_number_of_results_equal_to_number_of_rolls_parameter() {
        val die = NumericDie.withBounds(1, 6)
        val numberOfRolls = (1..10000).random()

        assertEquals(numberOfRolls, die.rollMultiple(numberOfRolls).size)
    }

    @Test
    fun roll_multiple_does_not_work_with_less_than_two_rolls() {
        val die = NumericDie.withBounds(1, 6)

        assertFailsWith<IllegalArgumentException> { die.rollMultiple(1) }
    }
}