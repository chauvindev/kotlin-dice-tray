package dev.chauvin.dicetray.dice.nonnumeric

import dev.chauvin.dicetray.dice.Die
import dev.chauvin.dicetray.dice.Face
import dev.chauvin.dicetray.roll.RollResult
import dev.chauvin.dicetray.roll.nonnumeric.NonNumericRollResult

/**
 * The NonNumericDie class is a concrete implementation of the [Die] interface class used
 * to create and roll dice that have non-integer [faces] (represented as strings).
 */
public open class NonNumericDie(
    faces: List<String>
): Die<String> {

    override val faces: List<Face<String>> by lazy { faces.map { Face(it) } }

    /**
     * Roll the die and return the result.
     *
     * @return NonNumericRollResult
     */
    override fun roll(numberOfRolls: Int): RollResult<String> {
        val roll = this.faces.random().value
        return NonNumericRollResult(roll)
    }

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<NonNumericRollResult>
     * @throws IllegalArgumentException
     */
    @Deprecated(
        message = "This function is deprecated and may be removed in future version.",
        replaceWith = ReplaceWith("roll()"),
        level = DeprecationLevel.WARNING
    )
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