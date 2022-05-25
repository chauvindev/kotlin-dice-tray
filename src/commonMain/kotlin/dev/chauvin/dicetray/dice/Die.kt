package dev.chauvin.dicetray.dice

import dev.chauvin.dicetray.roll.RollModifier
import dev.chauvin.dicetray.roll.RollResult

/**
 * Die defines the basic properties and functionality needed to create a rollable die with
 * a number of [faces].
 */
public interface Die <T: Any> {

    public val faces: List<Face<T>>

    /**
     * Roll the die and return the result.
     */
    public fun roll(numberOfRolls: Int = 1, rollModifiers: List<RollModifier<T>> = emptyList()): List<RollResult<T>>
}