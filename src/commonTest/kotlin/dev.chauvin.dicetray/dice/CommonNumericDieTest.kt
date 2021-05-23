package dev.chauvin.dicetray.dice

import dev.chauvin.dicetray.roll.RollModifier
import dev.chauvin.dicetray.roll.RollResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class CommonNumericDieTest {
    @Test
    fun test_shortcut_dice_bounds() {
        val dice  = listOf(
            NumericDie.d4(),
            NumericDie.d6(),
            NumericDie.d8(),
            NumericDie.d10(),
            NumericDie.d12(),
            NumericDie.d20(),
            NumericDie.d100()
        )

        val lowerBounds = dice.map { it.lowerBound }
        val upperBounds = dice.map { it.upperBound }

        val bounds = lowerBounds + upperBounds

        assertEquals(
            listOf(1, 1, 1, 1, 1, 1, 1, 4, 6, 8, 10, 12, 20, 100),
            bounds
        )
    }

    @Test
    fun lower_bound_cannot_be_greater_than_upper_bound() {
        /**
         * Trying to initialize a Die with a lower bound greater than
         * the upper bound should throw an error.
         */
        val lowerBound = 3
        val upperBound = 2

        assertFailsWith<IllegalArgumentException> {
            NumericDie(lowerBound, upperBound)
        }
    }

    @Test
    fun roll_function_returns_roll_result_object() {
        val die = NumericDie(1, 6)

        assertIs<RollResult>(die.roll())
    }

    @Test
    fun roll_result_is_sum_of_roll_and_modifiers() {
        val modifiers = listOf(
            RollModifier(-3),
            RollModifier(4),
            RollModifier(5)
        )
        val die = NumericDie(1, 6, modifiers)
        val roll = die.roll()

        assertEquals(roll.value, roll.rawValue + roll.modifiers.sumOf {it.value})
    }

    @Test
    fun roll_multiple_returns_a_list_of_roll_result_objects() {
        val die = NumericDie(1, 6)

        assertIs<List<RollResult>>(die.rollMultiple(4))
    }

    @Test
    fun roll_multiple_returns_a_number_of_results_equal_to_number_of_rolls_parameter() {
        val die = NumericDie(1, 6)
        val numberOfRolls = (1..10000).random()

        assertEquals(numberOfRolls, die.rollMultiple(numberOfRolls).size)
    }

    @Test
    fun roll_multiple_does_not_work_with_less_than_two_rolls() {
        val die = NumericDie(1, 6)

        assertFailsWith<IllegalArgumentException> { die.rollMultiple(1) }
    }

}