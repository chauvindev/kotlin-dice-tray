package dev.chauvin.dicetray.dice

import dev.chauvin.dicetray.roll.NumericRollModifier
import dev.chauvin.dicetray.roll.NumericRollResult

/**
 * NumericDie is an implementation of the Die interface. Each numeric die has a [lowerBound]
 * (i.e., the lowest number that can be rolled), an [upperBound] (i.e., the highest
 * number that can be rolled), and a list of applicable [modifiers].
 */
class NumericDie (
    val lowerBound: Int,
    val upperBound: Int,
    val modifiers: List<NumericRollModifier> = emptyList()
) : Die<Int> {

    init {
        if (lowerBound > upperBound) {
            throw (IllegalArgumentException(
                "The value of lowerBound must be smaller than the value of upperBound." +
                        " lowerBound: $lowerBound, upperBound: $upperBound"
            ))
        }
    }

    /**
     * Roll the die and return the result.
     *
     * @return NumericRollResult
     */
    override fun roll(): NumericRollResult {
        val roll = (lowerBound..upperBound).random()
        return NumericRollResult(roll + modifiers.sumOf { it.value }, modifiers, roll)
    }

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<NumericRollResult>
     * @throws IllegalArgumentException
     */
    override fun rollMultiple(numberOfRolls: Int): List<NumericRollResult> {
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
     * Provide shortcuts for most common die types.
     */
    companion object {
        fun d4(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie(1, 4, modifiers)
        fun d6(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie(1, 6, modifiers)
        fun d8(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie(1, 8, modifiers)
        fun d10(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie(1, 10, modifiers)
        fun d12(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie(1, 12, modifiers)
        fun d20(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie(1, 20, modifiers)
        fun d100(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie(1, 100, modifiers)
    }


}