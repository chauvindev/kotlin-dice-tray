package dev.chauvin.dicetray.roll

/**
 * The RollResult interface represents the outcome of a roll and must, at minimum,
 * hold the [value] of the roll.
 */
interface RollResult<T: Any> {
    val value: T
}