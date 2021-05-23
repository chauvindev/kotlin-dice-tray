package dev.chauvin.dicetray.roll

/**
 * A NumericRollModifier is a combination of a Number [value] that is to be applied to a die roll,
 * along with an optional [reason] for this modifier.
 */
data class NumericRollModifier <T: Number> (
    override val value: T,
    override val reason: String? = null
): RollModifier<T>