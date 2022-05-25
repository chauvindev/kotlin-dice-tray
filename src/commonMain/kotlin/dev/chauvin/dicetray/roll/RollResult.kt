package dev.chauvin.dicetray.roll

/**
 * The RollResult interface represents the outcome of a roll and must, at minimum,
 * hold the [value] of the roll.
 */
public data class RollResult<T: Any>(
    public val value: T,
    public val modifiers: List<RollModifier<T>>?,
    public val rawValue: T
)