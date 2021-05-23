package dev.chauvin.dicetray.roll.numeric

import dev.chauvin.dicetray.roll.RollResult

/**
 * A NumericRollResult object represents the final outcome of a numeric die roll, and holds the [value] of the roll
 * after all [modifiers] are applied. The initial [rawValue] of the roll before any modifiers is
 * also available.
 */
data class NumericRollResult <T: Number>(
    override val value: T,
    val modifiers: List<NumericRollModifier<T>>,
    val rawValue: T
): RollResult<T>