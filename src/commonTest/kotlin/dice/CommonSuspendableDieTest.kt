package dice

import kotlin.test.Test
import kotlin.test.assertEquals

class CommonSuspendableDieTest {
    @Test
    fun test_shortcut_dice_bounds() {
        val dice  = listOf(
            SuspendableDie.d4(),
            SuspendableDie.d6(),
            SuspendableDie.d8(),
            SuspendableDie.d10(),
            SuspendableDie.d12(),
            SuspendableDie.d20(),
            SuspendableDie.d100()
        )

        val lowerBounds = dice.map { it.lowerBound }
        val upperBounds = dice.map { it.upperBound }

        val bounds = lowerBounds + upperBounds

        assertEquals(
            listOf(1, 1, 1, 1, 1, 1, 1, 4, 6, 8, 10, 12, 20, 100),
            bounds
        )
    }
}