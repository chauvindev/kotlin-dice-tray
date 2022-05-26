package dev.chauvin.dicetray.core.dice

/** Represents a face on a die, holding a particular [value]. */
public data class Face<T: Any>(
    public val value: T
)