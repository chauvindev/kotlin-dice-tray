package dev.chauvin.dicetray.dice.numeric

import dev.chauvin.dicetray.dice.Die
import dev.chauvin.dicetray.roll.numeric.NumericRollModifier
import dev.chauvin.dicetray.roll.numeric.NumericRollResult

/**
 * The NumericDie class is a concrete implementation of the [Die] interface class used
 * to create and roll dice that have all-integer [faces].
 */
class NumericDie(
    override val faces: List<Int>,
    val modifiers: List<NumericRollModifier> = emptyList()
) : Die<Int> {

    override fun roll(): NumericRollResult {
        val roll = this.faces.random()
        return NumericRollResult(roll + modifiers.sumOf { it.value }, modifiers, roll)
    }

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
     * Provide shortcuts for most common integer dice.
     */
    companion object {
        fun d4(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie((1..4).toList(), modifiers)
        fun d6(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie((1..6).toList(), modifiers)
        fun d8(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie((1..8).toList(), modifiers)
        fun d10(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie((1..10).toList(), modifiers)
        fun d12(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie((1..12).toList(), modifiers)
        fun d20(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie((1..20).toList(), modifiers)
        fun d100(modifiers: List<NumericRollModifier> = emptyList()) = NumericDie((1..100).toList(), modifiers)

        fun withBounds(lowerBound: Int, upperBound: Int, modifiers: List<NumericRollModifier> = emptyList()): NumericDie {
            if (lowerBound > upperBound) {
                throw (IllegalArgumentException(
                    "The value of lowerBound must be smaller than the value of upperBound." +
                            " lowerBound: $lowerBound, upperBound: $upperBound"
                ))
            }

            val faces = (lowerBound..upperBound).toList()
            return NumericDie(faces, modifiers)
        }
    }
}