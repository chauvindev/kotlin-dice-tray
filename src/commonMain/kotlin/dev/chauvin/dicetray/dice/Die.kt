package dev.chauvin.dicetray.dice

import dev.chauvin.dicetray.roll.RollResult

/**
 * Die defines the basic properties and functionality needed to create a rollable die with
 * a number of [faces].
 */
interface Die <T: Any> {

    val faces: List<T>

    /**
     * Roll the die and return the result.
     *
     * @return RollResult
     */
    fun roll(numberOfRolls: Int = 1): RollResult<T>

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<RollResult>
     * @throws IllegalArgumentException
     */
    @Deprecated(
        message = "This function is deprecated and may be removed in future version.",
        replaceWith = ReplaceWith("roll()"),
        level = DeprecationLevel.WARNING
    )
    fun rollMultiple(numberOfRolls: Int): List<RollResult<T>>
}