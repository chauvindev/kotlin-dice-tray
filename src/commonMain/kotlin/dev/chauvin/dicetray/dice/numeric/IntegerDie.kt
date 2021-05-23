package dev.chauvin.dicetray.dice.numeric

import dev.chauvin.dicetray.roll.numeric.NumericRollModifier
import dev.chauvin.dicetray.roll.numeric.NumericRollResult

/**
 * The IntegerDie class is a concrete implementation of the [NumericDie] abstract class used
 * to create and roll dice that have all-integer faces.
 */
class IntegerDie(
    override val lowerBound: Int,
    override val upperBound: Int,
    override val modifiers: List<NumericRollModifier<Int>> = emptyList()
) : NumericDie<Int>() {

    init {
        if (lowerBound > upperBound) {
            throw (IllegalArgumentException(
                "The value of lowerBound must be smaller than the value of upperBound." +
                        " lowerBound: $lowerBound, upperBound: $upperBound"
            ))
        }
    }

    override fun roll(): NumericRollResult<Int> {
        val roll = (lowerBound..upperBound).random()
        return NumericRollResult(roll + modifiers.sumOf { it.value }, modifiers, roll)
    }

    override fun rollMultiple(numberOfRolls: Int): List<NumericRollResult<Int>> {
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
     * Provide shortcuts for most common integer dice.
     */
    companion object {
        fun d4(modifiers: List<NumericRollModifier<Int>> = emptyList()) = IntegerDie(1, 4, modifiers)
        fun d6(modifiers: List<NumericRollModifier<Int>> = emptyList()) = IntegerDie(1, 6, modifiers)
        fun d8(modifiers: List<NumericRollModifier<Int>> = emptyList()) = IntegerDie(1, 8, modifiers)
        fun d10(modifiers: List<NumericRollModifier<Int>> = emptyList()) = IntegerDie(1, 10, modifiers)
        fun d12(modifiers: List<NumericRollModifier<Int>> = emptyList()) = IntegerDie(1, 12, modifiers)
        fun d20(modifiers: List<NumericRollModifier<Int>> = emptyList()) = IntegerDie(1, 20, modifiers)
        fun d100(modifiers: List<NumericRollModifier<Int>> = emptyList()) = IntegerDie(1, 100, modifiers)
    }
}