# Kotlin Dice Tray
<img src="https://img.shields.io/maven-central/v/dev.chauvin/kotlin-dice-tray">

Kotlin Dice Tray is a small Multiplatform library which allows you to create and roll dice. It is meant to be simple to use yet flexible. For this reason,
it provides shortcuts to many standard dice (e.g., d20, d6), while also giving the user the ability to create their own dice. It currenty provides support for numeric dice (with integer faces) and non-numeric dice (with string faces).

## Installation
Kotlin Dice Tray is on MavenCentral and can be added to your project as follows:

### Maven
``` xml
<dependency>
    <groupId>dev.chauvin</groupId>
    <artifactId>kotlin-dice-tray</artifactId>
    <version>1.0.3</version>
</dependency>
```

### Gradle
```
dependencies {
    implementation 'dev.chauvin:kotlin-dice-tray:1.0.3'
}
```
Make sure you have <code>mavenCentral()</code> in the list of repositories
```
repository {
    mavenCentral()
}
```

### Gradle Kotlin DSL
```
dependencies {
    implementation("dev.chauvin:kotlin-dice-tray:1.0.3")
}
```

Make sure you have <code>mavenCentral()</code> in the list of repositories
```
repository {
    mavenCentral()
}
```

### Multiplatform
```
commonMain {
    dependencies {
        implementation("dev.chauvin:kotlin-dice-tray:1.0.3")
    }
}
```

Make sure you have <code>mavenCentral()</code> in the list of repositories
```
repository {
    mavenCentral()
}
```
## Using Kotlin Dice Tray
Kotlin Dice Tray currently offers two types of die: NumericDie and NonNumericDie. The former is akin to traditional dice with integer faces, while the latter allows for some more unconventional die to be created (e.g., a die consisting of faces such as: "success", "failure", "great success", "catastrophic failure"). Below are examples of both of how to use both of these die types.

### NumericDie
A NumericDie can be created by passing a list of all the possible <code>faces</code>. **Please note that you are under no obligation to have each face represented only once.**
``` kotlin
val balancedFaces = listOf(1, 2, 3, 4, 5, 6)
val balancedDie = NumericDie(faces) // This creates a 6-sided die

val unbalancedFaces = listOf(1, 1, 1, 1, 1, 6)
val unbalancedDie = NumericDie(unbalancedFaces)
```
For convenience, the <code>NumericDie.withBounds()</code> function is available to quickly create a balanced die with faces ranging from a given <code>lowerBound</code> to
<code>upperBound</code>:
``` kotlin
val die = NumericDie.withBounds(lowerBound = 1, upperBound = 6) //This is equivalent to NumericDie(listOf(1, 2, 3, 4, 5, 6))
```

For the most common types of numeric die (e.g., d6, d10, d20), the NumericDie class also provides shortcuts:
``` kotlin
val d4 = NumericDie.d4()
val d6 = NumericDie.d6()
val d8 = NumericDie.d8()
val d10 = NumericDie.d10()
val d12 = NumericDie.d12()
val d20 = NumericDie.d20()
val d100 = NumericDie.d100()
```

A NumericDie can also have one or more **modifiers**. There are instances of the NumericRollModifier class, which consists of an integer value to be applied
to each roll, and an optional reason for the modifier.By default, instances of the NumericDie class do not have any modifiers. To create a die with modifiers:

``` kotlin
val faces = listOf(1, 2, 3, 4, 5, 6)
val plusThreeModifier = NumericRollModifier(3, "For a good idea")
val minusTwoModifier = NumericRollModifier(-2, "Because they did not bring snacks to game night")
val plusSixModifier = NumericRollModifier(6, "For exceptional gusto")

val die = NumericDie(faces, listOf(plusThreeModifier)) // Creates a 6-sided die with a bonus +3 to every roll.
val anotherDie = NumericDie(faces, listOf(minusTwoModifier)) // Creates a 6-sided die with a minus 2 to every roll)
val dieAnotherDay = NumericDie(faces), listOf(plusThreeModifier, minusTwoModifier, dieAnotherDay) // Creates a 6-sided die with a +7 to every roll (+3, -2, +6)
```

Of course, modifiers can also be used with the NumericDie.withBounds() function:
```kotlin
val plusThreeModifier = NumericRollModifier(3, "For a good idea")
val die = NumericDie.withBounds(1, 6, listOf(plusThreeModifier))
```

And the same is true for the shortcuts to common dice:
```kotlin
val plusThreeModifier = NumericRollModifier(3, "For a good idea")
val die = NumericDie.d20(listOf(plusThreeModifier))
```

### NonNumericDie
A NonNumericDie can be created by passing a list of all the possible <code>faces</code>. **Please note that you are under no obligation to have each face represented only once.**

``` kotlin
val balancedFaces = listOf("success", "failure", "great success", "horrible failure", "neutral")
val balancedDie = NonNumericDie(faces) // This creates a 6-sided die

val unbalancedFaces = listOf(
    "success",
    "catastrophic failure",
    "catastrophic failure",
    "catastrophic failure",
    "catastrophic failure",
    "catastrophic failure"
) // Try to get out of this situation!
val unbalancedDie = NonNumericDie(unbalancedFaces)
```

NonNumericDie do not currently support any modifiers.

### Rolling a die
Rolling a die is quite simple: call the <code>roll()</code> function. This is the same for NumericDie and NonNumericDie
``` kotlin
val numericDie = NumericDie.d20()
val result = numericDie.roll()
```
This returns a <code>NumericRollResult</code> object which contains <code>value</code> of
the roll after all <code>modifiers</code> have been applied. The original,
<code>rawValue</code> of the roll without modifiers is also available.

``` kotlin
val faces = listOf("success", "failure", "great success", "horrible failure", "neutral")
val nonNumericDie = NonNumericDie(faces)
val result = nonNumericDie.roll()
```
This returns a <code>NonNumericRollResult</code> object which contains <code>value</code> of
the roll. While this is currently just a wrapper around the value of the roll, NonNumericRollResult is used
for consistency between NumericDie and NonNumericDie, as well as to not break implementations should modifiers
be supported for NonNumericDie in the future (at which point NonNumericRollResult would need to contain the list
of modifiers that were applied).

If you need multiple rolls, you can use the <code>rollMultiple(numberOfRolls)</code> function:
``` kotlin
val numericDie = NumericDie.d20()
val numericResults = numericDie.rollMultiple(5) // Roll the die 5 times and return a list of NumericRollResult objects

val nonNumericDie = NonNumericDie(faces)
val nonNumericResults = nonNumericDie.rollMultiple(5) // Roll the die 5 times and return a list of NonNumericRollResult objects.
```

## What's Next?
This is a very simple library and is meant to remain so. However, that does not mean there is no room for growth. One of the planned features is
a way to archive rolls in order to get a replay of every roll in order. Other suggestions are welcome!