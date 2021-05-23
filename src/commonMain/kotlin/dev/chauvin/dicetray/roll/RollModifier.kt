package dev.chauvin.dicetray.roll

/**
 * A RollModifier is a combination of a [value] that is to be applied to a die roll,
 * along with an optional [reason] for this modifier.
 */
interface RollModifier <T: Any> {
    val value: T
    val reason: String?
}