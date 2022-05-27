package dev.chauvin.dicetray.dsl

import dev.chauvin.dicetray.core.roll.RollModifier

@DiceTrayDsl
public interface RollModifierBuilder<T : Any> {
    /**
     * Add a RollModifier in the context of the parent [dev.chauvin.dicetray.dsl.roll] function.
     */
    public fun modifier(reason: String? = null, operation: (T) -> T)

    /**
     * Build and return a list containing an arbitrary number of RollModifier objects created using the DSL.
     */
    public fun build(): List<RollModifier<T>>
}

internal class RollModifierBuilderImpl<T : Any> : RollModifierBuilder<T> {
    private val modifiers: MutableList<RollModifier<T>> = mutableListOf()

    override fun modifier(reason: String?, operation: (T) -> T) {
        this.modifiers.add(
            RollModifier(
                operation = operation,
                reason = reason
            )
        )
    }

    override fun build(): List<RollModifier<T>> {
        return this.modifiers.toList()
    }
}