package roll

/**
 * A RollResult object represents the final outcome of a die roll, and holds the [value] of the roll
 * itself, a list of applicable [modifiers], and the [modifiedValue] once the modifiers have been applied.
 */
data class RollResult(
   val value: Int,
   val modifiers: List<RollModifier>,
   val modifiedValue: Int
)