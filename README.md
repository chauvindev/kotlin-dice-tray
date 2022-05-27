# Kotlin Dice Tray
<img src="https://img.shields.io/maven-central/v/dev.chauvin/kotlin-dice-tray">

Kotlin Dice Tray is a small Multiplatform library which allows you to create and roll dice. It is meant 
to be simple to use, yet flexible. For this reason, Kotlin Dice Tray uses generic types, allowing the user to roll
basically anything, and apply modifications to the roll on the go.

> **WARNING**: Version 2.0.0 is a complete rewrite of the library, and is incompatible
> with previous version. I sincerely doubt anyone was using the previous versions, but in the
> event that someone did, they should know that updating to 2.0.0 will break existing code.
> 
> The decision to rewrite was made in part to increase the flexibility of the library, and also to facilitate 
> backwards compatibility going forward.

## Installation
Kotlin Dice Tray is on MavenCentral and can be added to your project as follows:

### Maven
``` xml
<dependency>
    <groupId>dev.chauvin</groupId>
    <artifactId>kotlin-dice-tray</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Gradle
```
dependencies {
    implementation 'dev.chauvin:kotlin-dice-tray:2.0.0'
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
    implementation("dev.chauvin:kotlin-dice-tray:2.0.0")
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
        implementation("dev.chauvin:kotlin-dice-tray:2.0.0")
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
The core unit of the Kotlin Dice Tray library is the Die interface, which specifies a single property
consisting of a list of Face objects.

### Creating a Die
Kotlin Dice Tray offers two ways to create a Die object.

#### Method 1: The createDie() function
A Die object can be created by passing a list of all the possible <code>faces</code>. The number of faces is 
arbitrary, and there is no obligation for each Face to be represented only once.
``` kotlin
// This creates a standard six-sided die.
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

// This creates a six-sided die with 50% 
//of the faces holding a value of 1. 
val duplicatedFacesDie = createDie(
    faces = listOf(
        Face(1),
        Face(1),
        Face(1),
        Face(2),
        Face(3),
        Face(4)
    )
)
```
This is certainly readable, but could look a bit nicer with some syntactic sugar...

#### Method 2: The Kotlin Dice Tray DSL
The Die objects presented in the previous section can alternatively be created using
the Kotlin Dice Tray DSL:
``` kotlin
// This creates a standard six-sided die.
val die = die<Int> { 
    faces {
        face { 1 }
        face { 2 }
        face { 3 }
        face { 4 }
        face { 5 }
        face { 6 }
    }
}

// This creates a six-sided die with 50% 
//of the faces holding a value of 1. 
val duplicatedFacesDie = die<Int> { 
    faces {
        face { 1 }
        face { 1 }
        face { 1 }
        face { 4 }
        face { 5 }
        face { 6 }
    }
}
```
For convenience, the Die interface provides methods to rapidly create standard integer dice (e.g., d20, d6):
``` kotlin
val d4 = Die.d4() // This creates a four-sided die with face values ranging from 1 to 4
val d6 = Die.d6() // This creates a six-sided die with face values ranging from 1 to 6
val d8 = Die.d8() // This creates an eight-sided die with face values ranging from 1 to 8
val d10 = Die.d10() // This creates a ten-sided die with face values ranging from 1 to 10
val d12 = Die.d12() // This creates a twelve-sided die with face values ranging from 1 to 12
val d20 = Die.d20() // This creates a twenty-sided die with face values ranging from 1 to 20
val d100 = Die.d100() // This creates a one-hundred-sided die with face values ranging from 1 to 100
```

### Beyond integer dice
Because both Die and Face have generic type parameters, it is entirely possible (and encouraged!) to build dice with
non-numeric face values. The type parameter on both Die and Face inherits from Any, allowing for great flexibility.
The only constraint is that the generic type parameter of Die and Face is the same. In practice, this means that all
the Face objects of a given Die object must be of the same generic type. The reason for this constraint will become
apparent shortly, when we discuss the ability to pass modifiers to dice rolls.

For now, let's show that it is entirely possible to create a Die object with String values:
``` kotlin
// Using the createDie() function
val greetingDie = createDie(
    faces = listOf(
        Face("Hello!"),
        Face("Good morning!"),
        Face("How do you do?"),
        Face("What's up?"),
        Face("Hi!")
    )
)

// Using the DSL
val greetingDie = die<String> {
    faces {
        face { "Hello!" }
        face { "Good morning!" }
        face { "How do you do?" }
        face { "What's up?" }
        face { "Hi!" }
    }
}
```

It is also possible to create a Die object with faces holding custom objects:
``` kotlin
data class MyCustomClass(
    val myCustomIntProperty: Int
    val myCustomBooleanProperty: Boolean 
)


// Using the createDie() function
val customClassDie = createDie(
    faces = listOf(
        Face(
            value = MyCustomClass(
                myCustomIntProperty = 1,
                myCustomBooleanProperty = true
            )
        ),
        Face(
            value = MyCustomClass(
                myCustomIntProperty = 2,
                myCustomBooleanProperty = false
            )
        ),
        Face(
            value = MyCustomClass(
                myCustomIntProperty = 3,
                myCustomBooleanProperty = true
            )
        ),
        Face(
            value = MyCustomClass(
                myCustomIntProperty = 4,
                myCustomBooleanProperty = false
            )
        )
    )
)

// Using the DSL
val customClassDie = die<MyCustomClass> {
    faces {
        face {
            MyCustomClass(
                myCustomIntProperty = 1,
                myCustomBooleanProperty = true
            )
        }
        
        face {
            MyCustomClass(
                myCustomIntProperty = 2,
                myCustomBooleanProperty = false
            )
        }
        
        face {
            MyCustomClass(
                myCustomIntProperty = 3,
                myCustomBooleanProperty = true
            )
        }
        
        face {
            MyCustomClass(
                myCustomIntProperty = 4,
                myCustomBooleanProperty = false
            )
        }
    }
}
```

It is even possible to pass lambdas as Face values, which can then be called at a later time
(e.g., after the die has been rolled):

``` kotlin
// Using the createDie() function.
val lambdaDie = createDie<(Int) -> Int>( // Type parameter needed here.
    faces = listOf(
        Face(value = { it + 1 }),
        Face(value = { it + 2 }),
        Face(value = { it + 3 }),
        Face(value = { it + 4 })
    )
) 

// Using the DSL
val lambdaDie = die<(Int) -> Int>( // Type parameter needed here.
    faces {
        face { { it + 1 } } 
        face { { it + 2 } } 
        face { { it + 3 } } 
        face { { it + 4 } } 
    }
) 
```

### Rolling a die
Once a Die object is created, it can of course be rolled:
``` kotlin
val die = Die.d6()

// Roll the die once
val result = die.roll()

// Roll the multiple times
die.roll(numberOfRolls = 2)
die.roll(numberOfRolls = 10)
die.roll(numberOfRolls = 25)
die.roll(numberOfRolls = 100)
die.roll(numberOfRolls = 500)

```
It is also possible to pass a list of <code>rollModifiers</code>.This list contains an arbitrary number of 
RollModifier objects, each of which defines an<code>operation</code> lambda to be applied to the result of the roll. 
Optionally, a <code>reason</code> for the modifier can be provided. Importantly, the type parameter of the modifier 
must be the same as the type parameter of the Die (and Face objects):

> **NOTE**: When the roll is called, the modifiers are applied **in the order in which they were added to the list of
> modifiers.** This means that the first modifier directly impacts the roll value, but the second (and subsequent) 
> modifiers operate of the result of applying the previous modifier(s) to the raw roll result.

``` kotlin
val modifier1 = RollModifier<Int>(
    operation = { it + 1 },
    reason = "Give an unfair advantage."
) // This will add 1 to the result of the roll of a Die it is attached to.

val modifier2 = RollModifier<Int>(
    operation = { it - 3 },
    reason = "Give an unfair disadvantage."
) // This will remove 3 from the result of applying modifier1 to the value of the roll.

val die = Die.d20()
val result = die.roll(rollModifiers = listOf(modifier1, modifier2))
```
There is also a DSL version of the roll() function:
``` kotlin
val die = Die.20()
val result = die.roll {
    modifiers {
        modifier("Give an unfair advantage") { it + 1 } // This will add 1 to the result of the roll.
        modifier("Give an unfaire disadvantage") { it - 3 } // This will remove 3 from the results of the roll after having applied the first modifier.
    }
}
```

### Getting the result of a roll
Calling either <code>roll()</code> function (standard or DSL version) returns a list of RollResult objects. Each 
RollResult has three properties: <code>value</code>, <code>rawValue</code>, and <code>modifiers</code>.
The <code>value</code> property represents the value of the roll **after** all <code>modifiers</code> have been applied. 
On the other hand, <code>rawValue</code> is the value of the roll **before** <code>modifiers</code> were applied.

## What's Next?
This is a very simple library and is meant to remain so. However, that does not mean there is no room for growth. 
One of the planned features is to allow the user to define observable dice, using Kotlin Flows. Other suggestions are welcome!