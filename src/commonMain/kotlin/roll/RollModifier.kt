package roll

/**
 * A RollModifier is a combination of an integer [value] that is to be applied to a die roll,
 * along with the [reason] for this modifier.
 */
data class RollModifier(
    val value: Int,
    val reason: String
)