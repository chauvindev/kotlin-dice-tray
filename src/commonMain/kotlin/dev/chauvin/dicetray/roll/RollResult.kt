package dev.chauvin.dicetray.roll

/**
 * A RollResult object represents the final outcome of a die roll, and holds the [value] of the roll
 * after all [modifiers] are applied. The initial [rawValue] of the roll before any modifiers is
 * also available.
 */
data class RollResult(
    val value: Int,
    val modifiers: List<RollModifier>,
    val rawValue: Int
)