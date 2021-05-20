package dice

import kotlinx.coroutines.CoroutineScope
import kotlin.test.Test

expect class TargetSpecificSuspendableDieTest {
    fun runBlockingTest(block: suspend (scope : CoroutineScope) -> Unit)

    @Test
    fun roll_function_returns_flow_of_roll_result()

    @Test
    fun roll_result_is_sum_of_roll_and_modifiers()

    @Test
    fun roll_multiple_returns_a_flow_of_roll_result_objects()

    @Test
    fun roll_multiple_returns_a_number_of_results_equal_to_number_of_rolls_parameter()

    @Test
    fun roll_multiple_does_not_work_with_less_than_two_rolls()
}