package dev.chauvin.dicetray.roll

/**
 * A RollModifier is a combination of an [operation] that is to be applied to a die roll,
 * along with an optional [reason] for applying this operation.
 */
public data class RollModifier <T: Any>(
    public inline val operation: (T) -> T,
    public val reason: String?
)