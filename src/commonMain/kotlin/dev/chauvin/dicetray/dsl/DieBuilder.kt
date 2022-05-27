package dev.chauvin.dicetray.dsl

import dev.chauvin.dicetray.core.dice.Die
import dev.chauvin.dicetray.core.dice.Face
import dev.chauvin.dicetray.core.dice.createDie
import dev.chauvin.dicetray.core.roll.RollResult

@DiceTrayDsl
public interface DieBuilder<T: Any> {
    /**
     * Add an arbitrary number of faces to a die, using the [dev.chauvin.dicetray.dsl.FaceBuilder.face] function.
     */
    public fun faces(block: FaceBuilder<T>.() -> Unit)

    /**
     * Build and return a Die object created using the DSL.
     */
    public fun build(): Die<T>
}

/**
 * The entry point for the Kotlin Dice Tray DSL.
 *
 * The die function allows one to define an arbitrary number of die faces, using the
 * [dev.chauvin.dicetray.dsl.DieBuilder.faces] function.
 */
public fun <T: Any> die(block: DieBuilder<T>.() -> Unit): Die<T> {
    return DieBuilderImpl<T>().apply(block).build()
}

/**
 * A convenient DSL-style function to add an arbitrary number of modifiers to a die roll,
 * using the [dev.chauvin.dicetray.dsl.RollBuilder.modifiers] function.
 */
public fun <T: Any> Die<T>.roll(
    numberOfRolls: Int = 1,
    rollModifiers: RollBuilder<T>.() -> Unit
): List<RollResult<T>> {

    val modifiers = RollBuilderImpl<T>().apply(rollModifiers).build()
    return this.roll(numberOfRolls, modifiers)
}

private class DieBuilderImpl<T: Any>: DieBuilder<T> {
    private val faces: MutableList<Face<T>> = mutableListOf()

    override fun faces(block: FaceBuilder<T>.() -> Unit) {
        this.faces.addAll(FaceBuilderImpl<T>().apply(block).build())
    }

    override fun build(): Die<T> = createDie(this.faces.toList())
}