package dev.chauvin.dicetray.core

import dev.chauvin.dicetray.core.dice.Die
import dev.chauvin.dicetray.core.dice.Face
import dev.chauvin.dicetray.core.dice.createDie
import dev.chauvin.dicetray.core.roll.RollModifier
import dev.chauvin.dicetray.core.roll.RollResult
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeUpperCase
import io.kotest.matchers.string.shouldNotContain
import io.kotest.matchers.types.shouldBeInstanceOf

class DieTest : FunSpec({
    test("die() function returns a valid implementation of the Die interface") {
        val die = createDie(
            listOf(
                Face(1),
                Face(2),
                Face(3),
                Face(4),
                Face(5),
                Face(6)
            )
        )

        die.shouldBeInstanceOf<Die<Int>>()
    }

    test("roll() function with numberOfRolls <= 1 returns an empty list of results") {
        val die = createDie(
            listOf(
                Face(1),
                Face(2),
                Face(3),
                Face(4),
                Face(5),
                Face(6)
            )
        )

        val results = die.roll(numberOfRolls = -1)

        results shouldBe emptyList()
    }

    test("Die faces can hold a lambda as value") {
        val die = createDie<(Int) -> Int>(
            listOf(
                Face(value = { it - 1 }),
                Face(value = { it - 2 }),
                Face(value = { it - 3 }),
                Face(value = { it - 4 })
            )
        )

        val result = die.roll()

        result.shouldBeInstanceOf<List<RollResult<(Int) -> Int>>>()
    }

    // Roll modifier tests
    test("roll() function applies any rollModifiers that are provided to alter the result of the roll") {
        val die = createDie(
            listOf(
                Face(1),
                Face(2),
                Face(3),
                Face(4),
                Face(5),
                Face(6)
            )
        )

        val modifiers = listOf<RollModifier<Int>>(
            RollModifier(
                operation = { it + 5 },
                reason = null
            ),
            RollModifier(
                operation = { it + 2 },
                reason = null
            ),
            RollModifier(
                operation = { it / 2 },
                reason = null
            )
        )

        val result = die.roll(rollModifiers = modifiers)

        var modifiedResult = result[0].rawValue
        modifiedResult += 5
        modifiedResult += 2
        modifiedResult /= 2

        result[0].value shouldBe modifiedResult
    }

    test("Dice with non-numeric faces can also have modifiers") {
        val die = createDie(
            listOf(
                Face("Face 1"),
                Face("Face 2"),
                Face("Face 3"),
                Face("Face 4"),
                Face("Face 5"),
                Face("Face 6")
            )
        )

        val modifiers = listOf<RollModifier<String>>(
            RollModifier(
                operation = { it.uppercase() },
                reason = null
            ),
            RollModifier(
                operation = { it.replace("e", "") },
                reason = null
            )
        )

        val results = die.roll(rollModifiers = modifiers)

        results[0].value.shouldBeUpperCase()
        results[0].value.shouldNotContain("e")
    }

    test("Die with lambda face values can also have roll modifiers") {
        val die = createDie<(Int) -> Int>(
            listOf(
                Face(value = { it - 4 }),
                Face(value = { it - 4 }),
                Face(value = { it - 4 }),
                Face(value = { it - 4 })
            )
        )

        val modifiers = listOf<RollModifier<(Int) -> Int>>(
            RollModifier(
                operation = { result ->  { result.invoke(it) + 1 } },
                reason = null
            ),
            RollModifier(
                operation = { result ->  { result.invoke(it) + 3 } },
                reason = null
            ),
            RollModifier(
                operation = { result ->  { result.invoke(it) - 1 } },
                reason = null
            )
        )

        val result = die.roll(rollModifiers = modifiers)
        val appliedResult = result[0].value.invoke(10)

        appliedResult shouldBe 9
    }
})