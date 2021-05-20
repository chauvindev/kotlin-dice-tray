package dice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import roll.RollModifier
import roll.RollResult

interface SuspendableDieInterface {
    /**
     * SuspendDieInterface is a suspendable version of (@see [DieInterface]). It provides the same facilities
     * as the basic DieInterface, but works within a coroutine context out of the box.
     */

    val lowerBound: Int
    val upperBound: Int
    val modifiers: List<RollModifier>

    suspend fun roll(): Flow<RollResult> = flow {
        val roll = (lowerBound..upperBound).random()
        emit(RollResult(roll, modifiers, roll + modifiers.sumOf { it.value }))
    }

    suspend fun rollMultiple(numberOfRolls: Int): Flow<RollResult> {
        if (numberOfRolls >= 2) {
            return (1..numberOfRolls).map {
                val roll = (lowerBound..upperBound).random()
                RollResult(roll, modifiers, roll + modifiers.sumOf { it.value })
            }.asFlow()
        }

        else {
            throw (IllegalArgumentException("Must request at least two rolls."))
        }
    }
}