package nl.elisabeth.adventofcodeinscala

@main def completeAdvent(args: String*): Unit =

  // RESOURCES
  val root = os.pwd / "src" / "main" / "resources"

  // OUTPUT
  println("\nOUTPUT OF ALL ADVENT OF CODE EXERCISES\n")
  printDay1Trebuchet()
  printDay2CubeConundrum()
  printDay3GearRatios()
  printDay4()
  printDay5()
  
  
  def printDay1Trebuchet(): Unit =
    val filePathTrebuchet: os.Path = root / "trebuchet.txt"
    val trebuchetLineStream: geny.Generator[String] = os.read.lines.stream(filePathTrebuchet)
  
    println(
      "Day 1: Trebuchet" +
        "\nPart 1 - Text file: Take the first and last digit in each line combined into a number. Sum numbers = " +
        Day1_Trebuchet.sumOfCombinedFirstAndLastDigitOfEachLine(trebuchetLineStream, Day1_Trebuchet.firstAndLastDigit) +
        "\nPart 2 - Text file: Digits can also be written out like 'two'. Sum numbers = " +
        Day1_Trebuchet.sumOfCombinedFirstAndLastDigitOfEachLine(trebuchetLineStream, Day1_Trebuchet.firstAndLastDigitOrWrittenNumber) +
        "\n")
  
  def printDay2CubeConundrum(): Unit =
    val filePathCubeConundrum: os.Path = root / "cube-conundrum.txt"
    val cubeConundrumLineStream: geny.Generator[String] = os.read.lines.stream(filePathCubeConundrum)
    val realCubeCounts: Map[String, Int] = Map("green" -> 13, "red" -> 12, "blue" -> 14)
  
    println(
      "Day 2: Cube Conundrum" +
        "\nPart 1 - Text file: Lines of game output. Which games would have been possible if the bag contained only " +
        "12 red cubes, 13 green cubes, and 14 blue cubes. Sum gameId's = " +
        Day2_CubeConundrum.sumGameIdsThatFitRealCubeCounts(cubeConundrumLineStream, realCubeCounts) +
        "\nPart 2 - Text file: per game (line), take the product of minimum needed cubes. Sum products = " +
        Day2_CubeConundrum.sumProductOfMinimumCubesPerGame(cubeConundrumLineStream) +
        "\n")
  
  def printDay3GearRatios(): Unit =
    val filePathGearRatios: os.Path = root / "gear-ratios.txt"
    val gearRatiosLineStream: geny.Generator[String] = os.read.lines.stream(filePathGearRatios)
  
    println(
      "Day 3: Gear Ratios" +
        "\nPart 1 - " +
        "\nPart 2 - " +
        "\n")
  
  def printDay4(): Unit = 
    println(
      "Day 4: " +
        "\nPart 1 - " +
        "\nPart 2 - " +
        "\n")
  
  def printDay5(): Unit = 
    println(
      "Day 5: " +
        "\nPart 1 - " +
        "\nPart 2 - " +
        "\n")
  