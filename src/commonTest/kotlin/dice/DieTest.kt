package dice

import roll.RollModifier
import roll.RollResult
import kotlin.test.*

class DieTest {
    @Test
    fun lower_bound_cannot_be_greater_than_upper_bound() {
        /**
         * Trying to initialize a Die with a lower bound greater than
         * the upper bound should throw an error.
         */
        val lowerBound = 3
        val upperBound = 2

        assertFailsWith<IllegalArgumentException> {
            Die(lowerBound, upperBound)
        }
    }

    @Test
    fun roll_function_returns_roll_result_object() {
        val die = Die(1, 6)

        assertIs<RollResult>(die.roll())
    }

    @Test
    fun roll_result_is_sum_of_roll_and_modifiers() {
        val modifiers = listOf(
            RollModifier(-3),
            RollModifier(4),
            RollModifier(5)
        )
        val die = Die(1, 6, modifiers)
        val roll = die.roll()

        assertEquals(roll.modifiedValue, roll.value + roll.modifiers.sumOf {it.value})
    }

    @Test
    fun roll_multiple_returns_a_list_of_roll_result_objects() {
        val die = Die(1, 6)

        assertIs<List<RollResult>>(die.rollMultiple(4))
    }

    @Test
    fun roll_multiple_returns_a_number_of_results_equal_to_number_of_rolls_parameter() {
        val die = Die(1, 6)
        val numberOfRolls = (1..10000).random()

        assertEquals(numberOfRolls, die.rollMultiple(numberOfRolls).size)
    }
}