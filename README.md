# Advent of Code 2023 in Scala
Educating myself in Functional Programming (FP) and Scala by practicing with the Advent of Code exercises.

This means this code is full of comments where I explain the code to myself.

## Resources
https://docs.scala-lang.org/tutorials/scala-for-java-programmers.html -> you're at the "Algebraic datatypes and pattern matching" chapter

## Steps to get started
Use the Scala installer on https://docs.scala-lang.org/getting-started/index.html to get started.
This installation includes the Scala compiler and Scala's build tool 'sbt' (literally "simple build tool" that compiles, runs and tests your scala code). Also some other command line based software is added, but you might not need those.
Run "sbt new scala/scala3.g8" to start a Scala3 project (pulls setup from a gitHub repository) and creates complete project folder, which you can turn into your own repository.
Intellij is the easiest IDE to use with Scala. Install the scala plugin (intellij settings > plugins).

## sbt - Scala's simple build tool
In Scala projects, sbt is the most commonly used build tool. It provides features specifically tailored for Scala and Java projects, such as incremental compilation, dependency management, and integration with Scala's ecosystem. It's well-suited for managing complex Scala builds, testing, and running Scala-specific tasks.
(while sbt is generally preferred for Scala projects due to its native support and features, there can be cases where Maven is used at a higher level, especially in mixed or multi-project environments.)
- compile code with `sbt compile`
- run code with `sbt run`
- start a Scala 3 REPL with `sbt console` (Read-Evaluate-Print Loop: an interactive programming environment that takes user inputs (reads), executes the inputs (evaluates), and returns the result to the user (prints). This cycle repeats (loops), allowing users to interactively write and test code snippets.)
- If you only type `sbt` you enter the sbt shell, this will drop you into the sbt shell with the prompt (>) where you can execute sbt commands directly. Quit the shell with `exit`.
By staying in the sbt shell, you avoid the overhead of repeatedly starting sbt and loading the build configuration, which can make your build process more efficient.
In build.sbt you can define dependencies for your project, like the MUnit testing framework.

## Testing
MUnit is a Scala testing library (framework) written on top of JUnit (https://scalameta.org/munit/docs/getting-started.html).
If you have experience with JUnit, it works intuitively. Extend the test classes as follows: "class exampleTest extends munit.FunSuite".
Ensure Intellij's JUnit plugin is enabled (settings > plugins).

## Basics Scala 
source: scala docs

Scala is short for "scalable", since software build in Scala is easily scaled. Scala runs on JVM, JavaScript and Native.
(Virtually all programming languages require some form of a runtime environment to execute their code, and this runtime environment typically runs on top of an operating system).
All classes from the java.lang package are imported by default. In the sbt build file you can add third party libraries, as seen for MUnit.
There is no method called main. Instead, you use the @main annotation which marks a method as an entry-point of the application.
This main program does not need to be wrapped in a class definition. Scala 3 supports top-level method definitions, which are ideal for program entry-points.
Program entry-points optionally take parameters, which are populated by the command line arguments: @main def HelloWorld(args: String*): Unit = ...
You can pass arguments to your @main method from the sbt shell by using the run command followed by the arguments.
The arguments passed from the command line are collected into a Seq[String]. You can use these arguments in your code as a sequence (similar to a list).

Scala is a pure object-oriented language in the sense that everything is an object, including numbers or functions. It differs from Java in that respect, since Java distinguishes primitive types (such as boolean and int) from reference types.
You can pass methods as arguments, store them in variables, and return them from other functions, all without special syntax (specific Scala FP functionality).
Even though return types can be inferred in Scala, it is best practise to put explicit result types for public members of classes. For local values in methods, it is encouraged to infer result types.

Scala has classes and these classes can take parameters (as opposed to java classes, where you need a constructor). All classes in Scala inherit from a super-class. When no super-class is specified, scala.AnyRef is implicitly used.


## Basics FP 
source: book "Grokking functional programming" by Michal Plachta

### As much code as possible is pure
A pure function
- returns only a single value
- calculates the return value based only on its arguments
- doesn't mutate any existing values 

### No side effects
Computations(domain logic) are separated from user interaction (client logic).
In functional programming you want to separate I/O interactions (print and read) from the game logic
You usually create separate functions for both, which you can call from the main function
The game logic functions can then be very pure.
Since I/O function only change state, they return "Unit". because every Scala expression must have some value, there is actually a singleton value of type Unit, written (). It carries no information.)

### Everything is immutable
So no vars.
No while loops or for loops or any loop. Use recursion.
No imperative exception handling with try / catch (use "Either", "Option", or the Try monad from Scala)
