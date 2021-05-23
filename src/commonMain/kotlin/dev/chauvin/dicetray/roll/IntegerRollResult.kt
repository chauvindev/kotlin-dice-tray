package dev.chauvin.dicetray.roll

/**
 * A NumericRollResult object represents the final outcome of a numeric die roll, and holds the [value] of the roll
 * after all [modifiers] are applied. The initial [rawValue] of the roll before any modifiers is
 * also available.
 */
data class IntegerRollResult(
    override val value: Int,
    val modifiers: List<IntegerRollModifier>,
    val rawValue: Int
): RollResult<Int>