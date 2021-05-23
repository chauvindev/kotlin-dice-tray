package dev.chauvin.dicetray.dice

import dev.chauvin.dicetray.roll.RollResult

/**
 * Die defines the basic properties and functionality needed to create a rollable die.
 */
interface Die <T: Any> {
    /**
     * Roll the die and return the result.
     *
     * @return RollResult
     */
    fun roll (): RollResult<T>

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<RollResult>
     * @throws IllegalArgumentException
     */
    fun rollMultiple(numberOfRolls: Int): List<RollResult<T>>
}