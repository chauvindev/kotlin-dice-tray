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
}