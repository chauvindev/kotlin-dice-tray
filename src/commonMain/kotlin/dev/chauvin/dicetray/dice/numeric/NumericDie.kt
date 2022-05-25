package dev.chauvin.dicetray.dice.numeric

import dev.chauvin.dicetray.dice.Die
import dev.chauvin.dicetray.roll.numeric.NumericRollModifier
import dev.chauvin.dicetray.roll.numeric.NumericRollResult

/**
 * The NumericDie class is a concrete implementation of the [Die] interface class used
 * to create and roll dice that have all-integer [faces].
 */
public open class NumericDie(
    override val faces: List<Int>,
    public val modifiers: List<NumericRollModifier> = emptyList()
) : Die<Int> {

    /**
     * Roll the die, apply any modifiers, and return the result.
     *
     * @return NumericRollResult
     */
    override fun roll(numberOfRolls: Int): NumericRollResult {
        val roll = this.faces.random()
        return NumericRollResult(roll + modifiers.sumOf { it.value }, modifiers, roll)
    }

    /**
     * Roll the die a number of times corresponding to [numberOfRolls] and return the results.
     *
     * @return List<NumericRollResult>
     * @throws IllegalArgumentException
     */
    @Deprecated(
        message = "This function is deprecated and may be removed in future version.",
        replaceWith = ReplaceWith("roll()"),
        level = DeprecationLevel.WARNING
    )
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
    public companion object {
        public fun d4(modifiers: List<NumericRollModifier> = emptyList()): NumericDie = NumericDie((1..4).toList(), modifiers)
        public fun d6(modifiers: List<NumericRollModifier> = emptyList()): NumericDie = NumericDie((1..6).toList(), modifiers)
        public fun d8(modifiers: List<NumericRollModifier> = emptyList()): NumericDie = NumericDie((1..8).toList(), modifiers)
        public fun d10(modifiers: List<NumericRollModifier> = emptyList()): NumericDie = NumericDie((1..10).toList(), modifiers)
        public fun d12(modifiers: List<NumericRollModifier> = emptyList()): NumericDie = NumericDie((1..12).toList(), modifiers)
        public fun d20(modifiers: List<NumericRollModifier> = emptyList()): NumericDie = NumericDie((1..20).toList(), modifiers)
        public fun d100(modifiers: List<NumericRollModifier> = emptyList()): NumericDie = NumericDie((1..100).toList(), modifiers)

        public fun withBounds(lowerBound: Int, upperBound: Int, modifiers: List<NumericRollModifier> = emptyList()): NumericDie {
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