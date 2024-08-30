package nl.elisabeth.adventofcodeinscala

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {

    println("\nOUTPUT OF ALL ADVENT OF CODE EXERCISES\n")
    printDay1Trebuchet()
    printDay2CubeConundrum()
    printDay3GearRatios()


    // TODO: this function signature lies! maybe curry it up?
    def readFileAsStream[T](fileName: String,
                         part1Description: String,
                         part2Description: String,
                         part1Function: LazyList[String] => T,
                         part2Function: LazyList[String] => T): Unit =
      val inputStream = getClass.getResourceAsStream(fileName)
      if (inputStream != null)
        try
          val lines = Source.fromInputStream(inputStream).getLines() //this is now an Iterator[String]
          val lazyLines: LazyList[String] = 
            LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None } // now a LazyList[String]
          println(part1Description + part1Function(lazyLines) + part2Description + part2Function(lazyLines) + "\n")
        finally inputStream.close()
      else println("\nResource not found\n")


    def printDay1Trebuchet(): Unit =
      println("Day 1: Trebuchet")
      readFileAsStream("/trebuchet.txt",
        "\nPart 1 - Text file: Take the first and last digit in each line combined into a number. Sum numbers = ",
        "\nPart 2 - Text file: Digits can also be written out like 'two'. Sum numbers = ",
        Day1_Trebuchet.part1SumOfCombinedFirstAndLastDigitOfEachLine,
        Day1_Trebuchet.part2SumOfCombinedFirstAndLastDigitOfEachLineIncludingWrittenNumbers)


    def printDay2CubeConundrum(): Unit =
      println("Day 2: Cube Conundrum")
      readFileAsStream("/cube-conundrum.txt",
        "\nPart 1 - Text file: Lines of game output. Which games would have been possible if the bag contained only "+
          "12 red cubes, 13 green cubes, and 14 blue cubes. Sum gameId's = ",
        "\nPart 2 - Text file: per game (line), take the product of minimum needed cubes. Sum products = ",
        Day2_CubeConundrum.part1SumGameIdsThatFitRealCubeCounts,
        Day2_CubeConundrum.part2SumProductOfMinimumCubesPerGame)


    def printDay3GearRatios(): Unit =
      println("Day 3: Gear Ratios")
//      val filePathGearRatios: os.Path = root / "gear-ratios.txt"
//      val gearRatiosLineStream: geny.Generator[String] = os.read.lines.stream(filePathGearRatios)

  }
}

/* Takeaways
   By using Iterator and LazyList.unfold, you can efficiently process large files without loading the entire file
   into memory. The key is that Iterator inherently supports lazy reading, and LazyList.unfold maintains this
   laziness during processing. This approach ensures that only the current line being processed is in memory,
   achieving the desired memory efficiency.
 */