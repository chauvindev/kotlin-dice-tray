package dice

import roll.RollModifier
import roll.RollResult

/**
 * DieInterface defines the basic properties and functionality needed to create a rollable die. Each
 * die has a [lowerBound] (i.e., the lowest number that can be rolled), an [upperBound] (i.e., the highest
 * number that can be rolled), and a list of applicable [modifiers]. By default, implementations of this interface
 * are not suspendable. (@see [SuspendableDieInterface]).
 */
interface DieInterface {
    val lowerBound: Int
    val upperBound: Int
    val modifiers: List<RollModifier>

    /**
     * Roll the die and return the result. Also logs the result to the internal roll history.
     *
     * @return RollResult
     */
    fun roll(): RollResult {
        val roll = (lowerBound..upperBound).random()
        return RollResult(roll, modifiers, roll + modifiers.sumOf { it.value })
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
}