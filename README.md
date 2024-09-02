# Advent of Code 2023 in Scala
Scala implementations of the Advent-Of-Code exercises to practice Scala and Functional Programming.

This means this code is full of comments where I explain the code to myself.
NB: A lot of comments are also just copy-pasted from the resources below.

## Software stack
- Java 22.0.2
- Scala 3.4.2
- Scalatest 3.2.18
- SBT 1.10.1

## Resources
- https://docs.scala-lang.org/tutorials/scala-for-java-programmers.html -> Nice introduction if you're familiar with Java (you're at the "Algebraic datatypes and pattern matching" chapter)
- https://tourofscala.com/ -> syntax for beginners. Small exercises with cute confirmations. (you're at Try)
- https://scalacenter.github.io/scala-advent-of-code/2023/puzzles -> compare your work to these examples to see if you could have done it more efficient / shorter
- https://www.scala-sbt.org/1.x/docs/Running.html
- https://www.coursera.org/learn/scala-functional-programming
  - especially: https://www.coursera.org/learn/scala-functional-programming/supplement/Sauv3/cheat-sheet
- book "Grokking functional programming" by Michal Plachta

## Steps to get started
Use the Scala installer on https://docs.scala-lang.org/getting-started/index.html to get started.
This installation includes the Scala compiler and Scala's build tool 'sbt' (literally "simple build tool" that compiles, runs and tests your scala code). Also some other command line based software is added, but you might not need those.
This installation results in executables of scala and sbt, but also adds a path in your environment variables so they can be directly accessed from your terminal. Don't forget to restart the terminal you use.
Check the installation by executing `scala --version` and `sbt --version` in your terminal. (sometimes it does not work in your IDE and you have to close your project and IDE and restart!)
Run "sbt new scala/scala3.g8" in your terminal (in any folder you wish) to start a Scala3 project (pulls setup from a gitHub repository) and creates complete project folder, which you can turn into your own repository.
Intellij is the easiest IDE to use with Scala. Install the scala plugin (intellij settings > plugins).

## sbt - Scala's simple build tool
In Scala projects, sbt is the most commonly used build tool. It provides features specifically tailored for Scala and Java projects, such as incremental compilation, dependency management, and integration with Scala's ecosystem. It's well-suited for managing complex Scala builds, testing, and running Scala-specific tasks.
(while sbt is generally preferred for Scala projects due to its native support and features, there can be cases where Maven is used at a higher level, especially in mixed or multi-project environments.)
- `sbt compile` compiles the code (i.e. in src/main/scala and src/main/java directories) -> You use this command when you want to check for compilation errors or when you want to ensure that your code compiles correctly before performing other tasks.
- `sbt run` runs the code
- `sbt test` compiles and runs all tests
- `sbt package` compiles your code and creates a JAR file that contains your compiled classes and any dependencies. -> You use this command when you want to create a deployable or executable artifact of your application. This is especially useful for distribution or running the application in different environments.
- `sbt clean` deletes all generated files in the target directory
- `sbt console` start a Scala 3 REPL (Read-Evaluate-Print Loop: an interactive programming environment that takes user inputs (reads), executes the inputs (evaluates), and returns the result to the user (prints). This cycle repeats (loops), allowing users to interactively write and test code snippets.)
- `sbt` you enter the sbt shell (prompt (>. Here you can execute sbt commands directly. Quit the shell with `exit`.
By staying in the sbt shell, you avoid the overhead of repeatedly starting sbt and loading the build configuration, which can make your build process more efficient (much faster! Recommended for everyday coding).
In build.sbt you can define dependencies for your project, like your testing library.

## Unit Testing
2 popular Scala testing libraries
1. **Scalatest** is the most widely used testing framework in the scala community (extensive documentation and community support). It has a wide variety of testing styles.
Extend the test classes as follows: class exampleSuite extends AnyFunSuite:
2. **MUnit** is designed to be a minimalistic testing framework, which makes it simpler and easier to learn and use. Its API is straightforward and intuitive. 
It's written on top of JUnit (https://scalameta.org/munit/docs/getting-started.html).  If you have experience with JUnit, it works intuitively. 
Extend the test classes as follows: "class exampleSuite extends munit.FunSuite" to let them use MUnit. 
Ensure Intellij's JUnit plugin is enabled (settings > plugins). At the moment of writing, 
the newest version of MUnit seems not to be able to run tests separately (scalameta.org/munit 1.0.0). 
When using MUnit, the assertEquals method also returns you the difference between the observed and expected values when a test fails.
Test classes in scala are named ClassNameSuite
Test syntax: `test("description") { ... }`

## Basics Scala

Scala is short for "scalable", since software build in Scala is easily scaled. Scala runs on JVM, JavaScript and Native runtime environments.
(Virtually all programming languages require some form of a runtime environment to execute their code, and this runtime environment typically runs on top of an operating system).
Scala is both Object Oriented and Functional.

All classes from the java.lang package are imported by default. In the sbt build file you can add third party libraries, as seen for the testing suite.

Scala is a pure object-oriented language in the sense that everything is an object, including numbers or functions. It differs from Java in that respect, since Java distinguishes primitive types (such as boolean and int) from reference types.
You can pass methods as arguments, store them in variables, and return them from other functions, all without special syntax (specific Scala FP functionality).
Even though return types can be inferred in Scala, it is best practise to put explicit result types for public members of classes (for the users). For local values in methods, it is encouraged to infer result types.

Scala has classes and these classes can take parameters (as opposed to java classes, where you need a constructor). An instance (object) of a Scala class in created with the keyword "new".
All classes in Scala inherit from a super-class. When no super-class is specified, scala.AnyRef is implicitly used. Scala has quite a big variety of types of classes. It's easy to be overwhelmed, but try to start with the generic class, the object class (can only have a single instance), the trait (similar to interfaces in other languages), and the case class (pure data class).

### Setting up a basic project (application)
In Scala, when you are using sbt (Simple Build Tool) to build an application, you typically set up your project with a main entry point.
1. Define the main entry point: the conventional way to specify the entry point for your application in Scala is to define an object with a main method. This object serves as the main class of your application.
   (such an Object class is a specific Scala class of which only one instance can exist). The main method here has to follow a particular convention (inherited from Java): they have to take a single argument of type ‘Array[String]’, and return a Unit.
   ```
   object MyApp {
      def main(args: Array[String]): Unit = {
        println("Hello, world!")
        // Your application logic here
      }
   }
   ```
2. Configure sbt: In your build.sbt file, you generally do not need to specify the main class if you are using the default main method in the object. sbt will automatically find the main method from objects in the src/main/scala directory.
   However, if you want to specify the main class explicitly (e.g., if you have multiple objects with main methods or if you want to be explicit), you can add the mainClass setting to your build.sbt file:
   `mainClass in Compile := Some("com.example.MyApp")`
3. You can then add extra classes that can be used in the MyApp Object class.


### Some functionalities and syntax that is new to me
- scala indentation is 2 spaces instead of 4!
- scala defaults to call-by-value
- recursive functions have to have a return type (enforced by the compiler). It is optional for other types of functions, though encouraged for documentation.
- **Option** is the way in Scala to handle null. It allows to encapsulate the concept of “is this value defined” or “does this variable contain a value“. Example:
  Some() is a constructor for creating an instance of the Some class, which is a subclass of Option. Some is used to wrap a value inside an Option. It signifies that there is a value present.

  ``` 
  val choice1: Boolean = true
  val result1: Option[Int] = if(choice1) None else Some(1)
  assert(result1.isEmpty)
  val choice2: Boolean = false
  val result2: Option[Int] = if(choice2) None else Some(1)
  assert(result2.isDefined)
  println("Congratulations ! Believe in yourself !")
  ```

- **val VS lazy val VS def**
  val: Computed once when declared
  lazy val: Computed once when called (and then used from memory)(can be useful whena value is not needed yet, saving time and resources, and when the order of initialization matter)
  def: Computed each time it is called


- `list.map` will just apply a function to each element of the list and return the list with the new values ( just like a for each from other languages.). map is part of what is called a functor. It can also be applied to an option (if it has some the action is performed on the value in the option, otherwise it stays none).
To apply the map function on all elements, instead of using `(a => a + 1)`, you could also use `(_ + 1)`.
- `list.filter(a => a > 10)` The filter method allows you to keep the elements of the list that return true for the test (which can be a lambda function).


- **by-name parameters** in a function: not evaluated until they are used within the function body. (in the function argument list written as parameterName: => ParameterType). Useful for lazy evaluation and creating custom control structures.
  (when passing normal values to a function, e.g. 5 + 3. this value is calculated before it is used in the function body.


- **case class**: There is technically not a big difference between normal classes and case classes, though the latter provides a lot of build-in advantages over normal classes. To instantiate an object from a case class, no need for the new keyword. Also the properties are public (can be accessed by objectname.propertyname).
Case classes can be seen as plain and immutable data-holding objects that should exclusively depend on their constructor arguments. They are useful for classes where
  * Case classes can be pattern matched
  * Case classes automatically define hashcode and equals
  * Case classes automatically define getter methods for the constructor arguments.


- **scala object**: As known, an object is an instance of a class, but you also have the _object_ keyword to create a class that doesn't have to be instantiated in Scala. It is used for static methods and fields (can be accessed without an instance of a class)
So you can group a set of functions and variables in a Scala object-class, but since there will only ever be one, you don't have to make a new instance of it, you just refers to its name to access the properties and methods.
Most commonly used when you want to define constant values that are common for several systems or classes.
If such an object has the same name as an existing class, it is a companion object. A companion object is exactly like an object, except that the class that shares the same name can access the private members.
It is commonly used when you need to have constant or helper methods related to this class. It also helps with memory: Let's say you have a large constant – maybe an array or some other complex object. If it is stored inside the class, then each instantiation will make a copy of this constant in the memory. If instead the constant is in the companion object, then the program will only have to create the constant once.


- **apply**: a "magic" method usually used in a companion object, but can also be used directly in a normal class. There is no need to call apply explicitly to execute it. 
Example of apply in normal class: Once you created an instance of a class (e.g. val personInstance = new Person("Alice", "Smith")), you can directly call its apply method by executing personInstance(). (if the apply method has any parameters, you would put those between the parentheses of coure) 
In a companion Object, you can use the apply function to create an extra constructor so to speak, so you can create instances with a different amount of parameters.
Example of functional code with apply used in different ways can be found in the applyMethod class.

- When a function recursively calls more than one function, tail recursion is not an option!

- **scala Tuple**: a set of variables that can be of different types. e.g. val a: (Int, String) = (12, "abc") or alternative syntax val b: (Int, String) = 12 => "abc"
  To retrieve Tuple data: val first = a._1; val second = a._2 //(._1 is called an accessor)
  To swap data: a.swap 
  Whenever possible, you should use a case class rather than a Tuple. In the long run, it makes things much easier to manage and maintain.

- **algebraic datatypes**: combining different types into a single new type, e.g. a case class: 
  case class User(
    val userid: Int,
    val username: String,
    val password: String
    )
 A case class is a **product type**, where all subtypes contain data. There is also a **sum type**, where the subtypes are mutually exclusive and exhaustive, 
e.g. the boolean type has to be either true or false, never both. Other examples are enums or sealed traits (you define multiple classes and have to use one when making an object of the trait. This forces you to write the traits functions applicable for each subclass)
You can also use combinations.

- **Type constructors**: A type constructor in Scala (and other typed functional languages) is like a "blueprint" that creates new types from existing ones. For example, List is a type constructor. When you give it a type, like Int, it constructs a new type, List[Int].

- **Functors**: A functor is a concept from category theory, applied in programming. In Scala, a functor is a type (often a type constructor) that can be mapped over. If you have a List[Int], you can apply a function to every element in the list using map, transforming it into a List[String] or List[Double], for example. The key idea is that you can apply a function to a value inside a container (like a list) without changing the structure of the container.
  A functor is a type constructor F[_] that is map-able. We can apply a function to a value inside an F: F[T].map(f: T => U): F[U] e.g. List[Int].map(f: Int => String): List[String]
  An abstract view of mapping would be to see the functor as a box filled with elements, you take one element (value) out of the box, apply the function to it, and then put it back in a similar box.
  Think of “functor” as an interface: it says there needs to be a method map, without giving an implementation.
  (A functor that is not a type constructor but just a type, would be Box[T])

Examples: List, Option (and many more!)
- **Monads**: A monad is a type of functor with additional capabilities. Specifically, it allows for the chaining of operations that produce a value inside a context (like Option, List, or Future). In Scala, monads provide flatMap, which is like map but it "flattens" the result, allowing you to chain operations in a sequence.
  At its core, a Monad is a design pattern that represents computations as a series of steps. It’s a way to handle sequences of computations where each step depends on the result of the previous one, all within a certain context (like handling possible errors, working with asynchronous computations, etc.).
  In Scala, a Monad is typically defined by three main components:
  * Type Constructor (M[_]): A Monad operates on a type constructor M[_]. This is a type that takes another type as a parameter. For example, Option[_], List[_], Future[_], etc.
  * unit or pure: This is a function that takes a value of some type A and returns it wrapped in a Monad context M[A]. In Scala, this is usually achieved with the apply method or directly via constructors (e.g., Option(3) creates Some(3)): def pure[A](a: A): M[A]
  * flatMap: This is the key function that makes a Monad special. It allows you to take a value wrapped in a Monad, apply a function that returns a Monad, and then "flatten" the result
  In functional programming, side effects (like I/O, state changes, etc.) are often wrapped in Monads (like IO, Future, etc.), allowing you to manage them in a controlled way.
  “monad” is a sub-interface of “functor”. All Monads are Functors but not all Functors are Monads.
  Use case: If you apply multiple functions on an Option[Int], you might end up with an Option[Option[Int]]. You use flatmap to straighten that out if needed. Think of Monads as datatypes where it is possible to performs such a correction (another example List[List[List[String]]])

  Official definition: A monad is:
  a type constructor M[_]
  that implements a method: M[T].flatMap(f: T => M[U]): M[U]
  and a function: unit(x: T): M[T] In Scala, unit is like a constructor: it gives a way of making new things of type M[T].

Further reading: Category Theory, Kleisli

In FP, many useful design patterns have been implemented already on many relevant types or type constructors.
You are merely a client of the pattern.

You can define your own monads, BUT
- you’ll probably never need to;
- the specifics differ per language;
- if you do, you need to dive into the monad laws, and make sure that your monad satisfies the laws!

Here a list of existing monads:
- Collection types (Scala: List, Vector, Set, LazyList, …)
- Functional error handling (Scala: Option, Either[T,_], Try)
- IO monad (for purely functional I/O; see Haskell)
- Asynchronous code (Scala: Future)
- Reader/environment monad (use case: dependency injection)
- State monad (dealing with state)
- Writer monad (logging)
- ...

- collection methods
  * .map -> performs an action on each list element
  * .fold -> merges information from list elements, e.g. val sum: Int = flatList.fold(0)((x, y) => x + y)
  * .reduce
  * .filter -> returns a list with all elements that are true to a predicate
  * 

- **How to read files in Scala as streams**:
  * scala.io.Source library:  
    1. Get yourself a mutable stream
    
      `
      val lines = Source.fromFile(filename).getLines() // returns an Iterator[String] which is a mutable stream (lazy reading), which can only be passed once
      // operations
      lines.close()
      `
  
    2. Get yourself an immutable stream
    
      ```
      val inputStream = getClass.getResourceAsStream(fileName) // resource can be null / not found. Use if-statement to check
      val lines =  Source.fromInputStream(inputStream).getLines() 
      //this is now an Iterator[String] (getResourceAsStream(fileName): This gets an InputStream for a resource file. It's useful for files packaged with the application rather than files located on the filesystem.)
      val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None } // this is now a lazy list, which is an immutable stream which canbe used repeatedly
      // operations
      inputStream.close() // use a try-finally block, which makes sure the resource is closed even if an exception occurs during processing.
      ```

       Use .getResourceAsStream when you need to access a resource file as an input stream, typically for resources within the classpath (it returns a binary stream, i.e. It does not immediately interpret the contents of the file (e.g., as text lines).).
       Use .getLines when you need to read and process a text file line by line, either from a file or an input stream (it returns a stream of Strings).
       You can combine both by first using .getResourceAsStream to get the input stream and then Source.fromInputStream followed by .getLines to read the text lines from the resource.
  * os-lib library
    1. Similar approach. The advantage is that this library closes the file for you. Disadvantage is that this library is a bit less intuitive for beginners
    ```
    val resource = getClass.getClassLoader.getResource(resourceName) // resource can be null / not found: use a try-finally block
    val path = os.Path(resource.toURI)
    val lines: Iterator[String] = os.read.lines(path)
    LazyList.from(lines) // Convert Iterator to LazyList
    ```
  All these approaches are efficient and suitable for large files as they read lines lazily (both generate a stream).
  os-lib might be preferred if you need additional functionalities provided by the library. However, it is a bit less intuitive (e.g. takes some extra steps make file paths independent of system)
  scala.io.Source is part of the standard library and might be simpler to use for basic file reading operations.
  Beware: The file handle remains open for the duration of the processing, which could be a long time if the processing is slow. 
  This can be problematic if the file handle count is a limiting factor or if the program needs to open many files simultaneously.
  Also, properly handling exceptions and ensuring that the file is closed correctly is crucial. Failing to close the file properly can lead to resource leaks.


## Basics FP

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
