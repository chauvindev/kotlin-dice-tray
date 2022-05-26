package dev.chauvin.dicetray.core.roll

/**
 * Represents the result of a die roll.
 *
 * Each instance of the RollResult class holds the [value] obtained by rolling a die.
 * For convenience, the RollResult instance also holds the [rawValue] of the die roll,
 * along with a copy of all the [modifiers] that were applied to this [rawValue] to
 * compute [value].
 *
 * The RollResult interface represents the outcome of a roll and must, at minimum,
 * hold the [value] of the roll.
 */
public data class RollResult<T: Any>(
    public val value: T,
    public inline val modifiers: List<RollModifier<T>>?,
    public val rawValue: T
)