package dev.chauvin.dicetray.roll

/**
 * A NumericRollModifier is a combination of an integer [value] that is to be applied to a die roll,
 * along with an optional [reason] for this modifier.
 */
data class NumericRollModifier(
    override val value: Int,
    override val reason: String? = null
): RollModifier<Int>