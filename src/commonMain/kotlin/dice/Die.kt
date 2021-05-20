package dice

import roll.RollModifier

/**
 * Die is an implementation of DieInterface.
 */
class Die(
    override val lowerBound: Int,
    override val upperBound: Int,
    override val modifiers: List<RollModifier> = emptyList()
) : DieInterface {

    init {
        if (lowerBound > upperBound) {
            throw (IllegalArgumentException(
                "The value of lowerBound must be smaller than the value of upperBound." +
                        " lowerBound: $lowerBound, upperBound: $upperBound"
            ))
        }
    }

    /**
     * Provide shortcuts for most common die types.
     */
    companion object {
        fun d4(modifiers: List<RollModifier> = emptyList()) = Die(1, 4, modifiers)
        fun d6(modifiers: List<RollModifier> = emptyList()) = Die(1, 6, modifiers)
        fun d8(modifiers: List<RollModifier> = emptyList()) = Die(1, 8, modifiers)
        fun d10(modifiers: List<RollModifier> = emptyList()) = Die(1, 10, modifiers)
        fun d12(modifiers: List<RollModifier> = emptyList()) = Die(1, 12, modifiers)
        fun d20(modifiers: List<RollModifier> = emptyList()) = Die(1, 20, modifiers)
        fun d100(modifiers: List<RollModifier> = emptyList()) = Die(1, 100, modifiers)
    }
}