package dev.chauvin.dicetray.dsl

import dev.chauvin.dicetray.core.roll.RollModifier

@DiceTrayDsl
public interface RollBuilder <T: Any> {
    /**
     * Add an arbitrary number of RollModifier objects to a die roll, using the
     * [dev.chauvin.dicetray.dsl.RollModifierBuilder.modifier] function.
     */
    public fun modifiers(block: RollModifierBuilder<T>.() -> Unit)

    /**
     * Build and return a list containing an arbitrary number of RollModifier objects created using the DSL.
     */
    public fun build(): List<RollModifier<T>>
}

internal class RollBuilderImpl <T:Any> : RollBuilder<T> {
    private val modifiers: MutableList<RollModifier<T>> = mutableListOf()

    override fun modifiers(block: RollModifierBuilder<T>.() -> Unit) {
        this.modifiers.addAll(RollModifierBuilderImpl<T>().apply(block).build())
    }

    override fun build(): List<RollModifier<T>> = this.modifiers.toList()

}