package dev.chauvin.dicetray.dsl

import dev.chauvin.dicetray.core.dice.Face
import dev.chauvin.dicetray.core.dice.createDie
import dev.chauvin.dicetray.core.roll.RollModifier
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DslTest: FunSpec({
    test("The die() DSL function returns a valid die object") {
        val die = createDie(
            faces = listOf(
                Face(1),
                Face(2),
                Face(3),
                Face(4),
                Face(5),
                Face(6)
            )
        )

        val dslDie = die<Int> {
            faces {
                face { 1 }
                face { 2 }
                face { 3 }
                face { 4 }
                face { 5 }
                face { 6 }
            }
        }

        dslDie.faces shouldBe die.faces
    }

    test("The roll() DSL function returns results equivalent to the standard Die.roll function") {
        val die = die<Int> {
            faces {
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
            }
        }

        die.roll() shouldBe die.roll {  }
    }

    test("The DSL roll function applies modifiers properly") {
        val die = die<Int> {
            faces {
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
            }
        }

        val results = die.roll {
            modifiers {
                modifier { it + 5 }
                modifier { it + 2 }
            }
        }

        results[0].value shouldBe 1 + 5 + 2
    }

    test("RollModifier objects added through either roll functions are equivalent") {
        val die = die<Int> {
            faces {
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
                face { 1 }
            }
        }

        val standardResults = die.roll(
            rollModifiers = listOf(
                RollModifier(
                    operation = { it + 5 },
                    reason = null
                ),
                RollModifier(
                    operation = { it + 2 },
                    reason = null
                )
            )
        )

        val dslResults = die.roll {
            modifiers {
                modifier { it + 5 }
                modifier { it + 2 }
            }
        }

        standardResults[0].value shouldBe dslResults[0].value
    }
})