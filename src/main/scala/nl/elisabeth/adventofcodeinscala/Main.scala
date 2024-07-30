package nl.elisabeth.adventofcodeinscala

@main def completeAdvent(args: String*): Unit =

  // Scala docs way to read a file:
  // trouble shooting for finding the correct path to your data file: println(s"Current working directory: ${os.pwd}")
  val root = os.pwd // gets the current working directory
  val filePathTrebuchet: os.Path = root / "src" / "main" / "resources" / "trebuchet.txt"
  val filePathCubeConundrum: os.Path = root / "src" / "main" / "resources" / "cube-conundrum.txt"

  // Day 1 - Trebuchet
  println("Day 1: Trebuchet. Part1 - The first and last digit per line combined into a number, then the sum of all: "
    + Day1_Trebuchet.sumOfCombinedFirstAndLastDigitOfEachLine(filePathTrebuchet, Day1_Trebuchet.firstAndLastDigit))
  println("Day 1: Trebuchet. Part2 - Digits can also be written out like 'two'. Calculate the sum with this requirement: "
    + Day1_Trebuchet.sumOfCombinedFirstAndLastDigitOfEachLine(filePathTrebuchet, Day1_Trebuchet.firstAndLastDigitOrWrittenNumber))


  // Day 2 - Cube Conundrum
  val realCubeCounts: Map[String, Int] = Map("green" -> 13, "red" -> 12, "blue" -> 14)
  println("Day 2: Cube Conundrum. Part 1 - which games would have been possible if the bag contained only 12 red cubes, "
  + "13 green cubes, and 14 blue cubes. This is the sum: " 
    + Day2_CubeConundrum.sumGameIdsThatFitRealCubeCounts(filePathCubeConundrum, realCubeCounts))
  println("Day 2: Cube Conundrum. Part 2 - per game, take the product of minimum needed cubes, sum all those products: " 
    + Day2_CubeConundrum.sumProductOfMinimumCubesPerGame(filePathCubeConundrum))
