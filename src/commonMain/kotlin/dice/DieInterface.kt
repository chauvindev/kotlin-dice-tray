package dice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import roll.RollModifier
import roll.RollResult

/**
 * DieInterface defines the basic properties and functionality needed to create a rollable die. Each
 * die has a [lowerBound] (i.e., the lowest number that can be rolled), an [upperBound] (i.e., the highest
 * number that can be rolled), and a list of applicable [modifiers]. This interface provides both
 * synchronous and suspendable methods for rolling the die.
 */
interface DieInterface {
    val lowerBound: Int
    val upperBound: Int
    val modifiers: List<RollModifier>

    /**
     * Roll the die and return the result.
     *
     * @return RollResult
     */
    fun roll(): RollResult {
        val roll = (lowerBound..upperBound).random()
        return RollResult(roll, modifiers, roll + modifiers.sumOf { it.value })
    }

    /**
     * Roll the die and return the result.
     *
     * @return RollResult
     */
    suspend fun suspendableRoll(): Flow<RollResult> {
        return flowOf(roll())
    }

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<RollResult>
     * @throws IllegalArgumentException
     */
    fun rollMultiple(numberOfRolls: Int): List<RollResult> {
        if (numberOfRolls >= 2) {
           return (1..numberOfRolls).map {
                roll()
            }
        }

        else {
            throw (IllegalArgumentException("Must request at least two rolls."))
        }
    }

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return Flow<RollResult>
     * @throws IllegalArgumentException
     */
    suspend fun suspendableRollMultiple(numberOfRolls: Int): Flow<RollResult> {
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