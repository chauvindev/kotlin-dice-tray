package dev.chauvin.dicetray.dice.numeric

import dev.chauvin.dicetray.dice.nonnumeric.NonNumericDie
import dev.chauvin.dicetray.roll.nonnumeric.NonNumericRollResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class CommonNonNumericDieTest {
    @Test
    fun roll_function_returns_roll_result_object() {
        val die = NonNumericDie(
            listOf(
                "success",
                "failure",
                "success",
                "failure"
            )
        )

        assertIs<NonNumericRollResult>(die.roll())
    }

    @Test
    fun roll_multiple_returns_a_list_of_roll_result_objects() {
        val die = NonNumericDie(
            listOf(
                "success",
                "failure",
                "success",
                "failure"
            )
        )

        assertIs<List<NonNumericRollResult>>(die.rollMultiple(4))
    }

    @Test
    fun roll_multiple_returns_a_number_of_results_equal_to_number_of_rolls_parameter() {
        val die = NonNumericDie(
            listOf(
                "success",
                "failure",
                "success",
                "failure"
            )
        )
        val numberOfRolls = (1..10000).random()

        assertEquals(numberOfRolls, die.rollMultiple(numberOfRolls).size)
    }

    @Test
    fun roll_multiple_does_not_work_with_less_than_two_rolls() {
        val die = NonNumericDie(
            listOf(
                "success",
                "failure",
                "success",
                "failure"
            )
        )

        assertFailsWith<IllegalArgumentException> { die.rollMultiple(1) }
    }
}