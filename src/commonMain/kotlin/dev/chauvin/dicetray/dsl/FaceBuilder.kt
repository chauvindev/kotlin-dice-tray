package dev.chauvin.dicetray.dsl

import dev.chauvin.dicetray.core.dice.Face

@DiceTrayDsl
public interface FaceBuilder<T : Any> {
    /**
     * Add a Face in the context of the parent [dev.chauvin.dicetray.dsl.die] function.
     */
    public fun face(value: FaceBuilder<T>.() -> T)

    /**
     * Build and return a list containing an arbitrary number of Face objects created using the DSL.
     */
    public fun build(): List<Face<T>>
}

internal class FaceBuilderImpl<T : Any> : FaceBuilder<T> {
    private val faces: MutableList<Face<T>> = mutableListOf()

    override fun face(value: FaceBuilder<T>.() -> T) {
        this.faces.add(
            Face(
                value = FaceBuilderImpl<T>().value()
            )
        )
    }

    override fun build(): List<Face<T>> {
        return this.faces.toList()
    }

}