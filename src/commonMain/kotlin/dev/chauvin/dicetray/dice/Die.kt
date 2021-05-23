package dev.chauvin.dicetray.dice

import dev.chauvin.dicetray.roll.NumericRollResult

/**
 * Die defines the basic properties and functionality needed to create a rollable die.
 */
interface Die {
    /**
     * Roll the die and return the result.
     *
     * @return RollResult
     */
    fun roll(): NumericRollResult

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<RollResult>
     * @throws IllegalArgumentException
     */
    fun rollMultiple(numberOfRolls: Int): List<NumericRollResult>
}