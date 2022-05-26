package dev.chauvin.dicetray.core.dice

import dev.chauvin.dicetray.core.roll.RollModifier
import dev.chauvin.dicetray.core.roll.RollResult

/**
 * Die defines the basic properties and functionality needed to create a die with an arbitrary number of [faces].
 */
public interface Die <T: Any> {

    public val faces: List<Face<T>>

    /**
     * Roll the die a number of times equal to [numberOfRolls]. For each die rolled, apply [rollModifiers].
     */
    public fun roll(numberOfRolls: Int = 1, rollModifiers: List<RollModifier<T>> = emptyList()): List<RollResult<T>> {
        if (numberOfRolls <= 0) return emptyList()

        return (1..numberOfRolls).map {
            val rawValue = this.faces.random().value
            var modifiedValue = rawValue
            rollModifiers.forEach {modifier ->
                modifiedValue = modifier.operation.invoke(modifiedValue)
            }

            RollResult<T>(
                value = modifiedValue,
                modifiers = rollModifiers,
                rawValue = rawValue
            )
        }
    }
}

internal class BasicDie<T: Any>(override val faces: List<Face<T>>) : Die<T>

/**
 * Returns a Die with the provided [faces].
 */
public fun <T: Any> createDie(faces: List<Face<T>>): Die<T> {
    return BasicDie(faces)
}