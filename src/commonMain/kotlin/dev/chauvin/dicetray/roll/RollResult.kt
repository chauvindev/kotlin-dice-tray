package dev.chauvin.dicetray.roll

/**
 * The RollResult interface represents the outcome of a roll and must, at minimum,
 * hold the [value] of the roll.
 */
public interface RollResult<T: Any> {
    public val value: T
}