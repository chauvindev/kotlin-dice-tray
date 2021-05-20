package dice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.promise
import roll.RollModifier
import roll.RollResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

@DelicateCoroutinesApi
actual class TargetSpecificSuspendableDieTest {
    actual fun runBlockingTest(block: suspend (scope : CoroutineScope) -> Unit): dynamic = GlobalScope.promise { block(this) }

    @Test
    actual fun roll_function_returns_flow_of_roll_result() = runBlockingTest {
        val die = SuspendableDie(1, 6)

        assertIs<Flow<RollResult>>(die.roll())
    }

    @Test
    actual fun roll_result_is_sum_of_roll_and_modifiers() = runBlockingTest {
        val modifiers = listOf(
            RollModifier(-3),
            RollModifier(4),
            RollModifier(5)
        )
        val die = SuspendableDie(1, 6, modifiers)
        var roll: RollResult? = null

        die.roll().collect {roll = it }


        assertEquals(roll!!.modifiedValue, roll!!.value + roll!!.modifiers.sumOf {it.value})
    }

    @Test
    actual fun roll_multiple_returns_a_flow_of_roll_result_objects() = runBlockingTest {
        val die = SuspendableDie(1, 6)

        assertIs<Flow<RollResult>>(die.rollMultiple(4))
    }

    @Test
    actual fun roll_multiple_returns_a_number_of_results_equal_to_number_of_rolls_parameter() = runBlockingTest {
        val die = SuspendableDie(1, 6)
        val numberOfRolls = (1..10000).random()

        val results = mutableListOf<RollResult>()
        die.rollMultiple(numberOfRolls).collect { results.add(it) }

        assertEquals(numberOfRolls, results.size)
    }

    @Test
    actual fun roll_multiple_does_not_work_with_less_than_two_rolls() = runBlockingTest {
        val die = SuspendableDie(1, 6)

        assertFailsWith<IllegalArgumentException> { die.rollMultiple(1) }
    }

}