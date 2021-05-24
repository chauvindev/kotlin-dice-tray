package dev.chauvin.dicetray.dice.nonnumeric

import dev.chauvin.dicetray.dice.Die
import dev.chauvin.dicetray.roll.RollResult
import dev.chauvin.dicetray.roll.nonnumeric.NonNumericRollResult

/**
 * The NonNumericDie class is a concrete implementation of the [Die] interface class used
 * to create and roll dice that have non-integer [faces] (represented as strings).
 */
open class NonNumericDie(override val faces: List<String>): Die<String> {
    /**
     * Roll the die and return the result.
     *
     * @return NonNumericRollResult
     */
    override fun roll(): RollResult<String> {
        val roll = this.faces.random()
        return NonNumericRollResult(roll)
    }

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<NonNumericRollResult>
     * @throws IllegalArgumentException
     */
    override fun rollMultiple(numberOfRolls: Int): List<RollResult<String>> {
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