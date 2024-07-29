package nl.elisabeth.adventofcodeinscala

@main def completeAdvent(args: String*): Unit =

  // Scala docs way to read a file:
  // trouble shooting for finding the correct path to your data file: println(s"Current working directory: ${os.pwd}")
  val root = os.pwd // gets the current working directory
  val filePathTrebuchet: os.Path = root / "src" / "main" / "resources" / "trebuchet.txt"

  println("Day 1: Trebuchet?! Part1 - The first and last digit per line combined into a number, then the sum of all: " 
    + Day1_Trebuchet.sumOfCombinedFirstAndLastDigitOfEachLine(filePathTrebuchet, Day1_Trebuchet.firstAndLastDigit))
  println("Day 1, Trebuchet?! Part2 - Digits can also be written out like 'two'. Calculate the sum with this requirement: " 
    + Day1_Trebuchet.sumOfCombinedFirstAndLastDigitOfEachLine(filePathTrebuchet, Day1_Trebuchet.firstAndLastDigitOrWrittenNumber))