package dev.chauvin.dicetray.dice

import dev.chauvin.dicetray.roll.NumericRollModifier
import dev.chauvin.dicetray.roll.NumericRollResult

/**
 * NumericDie is an abstract implementation of the Die interface. Each numeric die has a [lowerBound]
 * (i.e., the lowest number that can be rolled), an [upperBound] (i.e., the highest
 * number that can be rolled), and a list of applicable [modifiers].
 */
abstract class NumericDie <T: Number>(

) : Die<T> {

    abstract val lowerBound: T
    abstract val upperBound: T
    abstract val modifiers: List<NumericRollModifier<T>>

    /**
     * Roll the die and return the result.
     *
     * @return NumericRollResult
     */
    abstract override fun roll(): NumericRollResult<T>
    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<NumericRollResult>
     * @throws IllegalArgumentException
     */
    abstract override fun rollMultiple(numberOfRolls: Int): List<NumericRollResult<T>>
}