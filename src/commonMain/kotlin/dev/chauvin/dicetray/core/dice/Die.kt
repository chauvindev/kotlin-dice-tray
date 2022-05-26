package dev.chauvin.dicetray.core.dice

import dev.chauvin.dicetray.core.roll.RollModifier
import dev.chauvin.dicetray.core.roll.RollResult

/**
 * Die defines the basic properties and functionality needed to create a die with an arbitrary number of [faces].
 */
public interface Die <T: Any> {

    public val faces: List<Face<T>>

    /**
     * Roll the die a number of times equal to [numberOfRolls]. For each die rolled, apply [rollModifiers].
     */
    public fun roll(numberOfRolls: Int = 1, rollModifiers: List<RollModifier<T>> = emptyList()): List<RollResult<T>>
}