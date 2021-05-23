# Kotlin Dice Tray
<img src="https://img.shields.io/maven-central/v/dev.chauvin/kotlin-dice-tray">

Kotlin Dice Tray is a small Multiplatform library which allows you to create and roll dice. It is meant to be simple to use yet flexible. For this reason,
it provides shortcuts to many standard dice (e.g., d20, d6), while also giving the user the ability to create their own dice.

## Installation
Kotlin Dice Tray is on MavenCentral and can be added to your project as follows:

### Maven
``` xml
<dependency>
    <groupId>dev.chauvin</groupId>
    <artifactId>kotlin-dice-tray</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Gradle
```
dependencies {
    implementation 'dev.chauvin:kotlin-dice-tray:1.0.2'
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
    implementation("dev.chauvin:kotlin-dice-tray:1.0.2")
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
        implementation("dev.chauvin:kotlin-dice-tray:1.0.2")
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
The core of the library is the Die class, which can represent any numerical die, as long as the <code>lowerBound</code>(the smallest number on the die)
is smaller than the <code>upperBound</code> (the largest number on the die). **Please note that at this time, the library only supports die for which each
number is equally likely to be rolled.**

### Creating Dice

The Die class has a companion object which can provide instances of some common die types:
``` kotlin
val d4 = Die.d4()
val d6 = Die.d6()
val d8 = Die.d8()
val d10 = Die.d10()
val d12 = Die.d12()
val d20 = Die.d20()
val d100 = Die.d100()
```

If these do not satisfy your requirements, you can also create an instance of the Die class speciying the <code>lowerBound</code> and <code>upperBound</code>:
``` kotlin
val customDie = Die(3, 26) /** create a die with faces from 3 to 26 **/
```

A die can also have one or more **modifiers**. These are integer values which are applied to each roll of the die and modify the final outcome.
By default, instances of the Die class do not have any modifiers. To create a die with modifiers:

``` kotlin
val die = Die(1, 20, listOf(3)) // creates a 20-sided die with a bonus +3 to every roll.
val anotherDie = Die(1, 20, listOf(-2) //creates a 20-sided die with a minus 2 to every roll.
val dieAnotherDay = Die(1, 20, listOf(3, -5)) //create a 20-sided die with a minus 2 to every roll (+3, -5).
```

Of course, modifiers can also be used with the companion object shortcuts:
```kotlin
val d20 = Die.d20(listOf(3))
```

### Rolling a die
Rolling a die is quite simple: call the <code>roll()</code> function.
``` kotlin
val die = Die.d20()
val result = die.roll()
```

This returns a <code>RollResult</code> object which contains <code>value</code> of
the roll after all <code>modifiers</code> have been applied. The original, 
<code>rawValue</code> of the roll without modifiers is also available.

If you need multiple rolls, you can use the <code>rollMultiple(numberOfRolls)</code> function:
``` kotlin
val die = Die.d20()
val results = die.rollMultiple(5) // rolls the die 5 times and returns a list of RollResult objects.
```

## What's Next?
This is a very simple library and is meant to remain so. However, that does not mean there is no room for growth. One of the planned features is
a way to archive rolls in order to get a replay of every roll in order. Other suggestions are welcome!