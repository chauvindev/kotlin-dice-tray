package dev.chauvin.dicetray.roll.numeric

import dev.chauvin.dicetray.roll.RollModifier

/**
 * A NumericRollModifier is a combination of a integer [value] that is to be applied to a die roll,
 * along with an optional [reason] for this modifier.
 */
data class NumericRollModifier (
    override val value: Int,
    override val reason: String? = null
): RollModifier<Int>