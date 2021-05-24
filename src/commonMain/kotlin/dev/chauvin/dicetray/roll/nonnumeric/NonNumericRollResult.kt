package dev.chauvin.dicetray.roll.nonnumeric

import dev.chauvin.dicetray.roll.RollResult

/**
 * A NonNumericRollResult object represents the outcome of a non-numeric die roll, and holds the [value] of the roll.
 */
data class NonNumericRollResult(override val value: String): RollResult<String>