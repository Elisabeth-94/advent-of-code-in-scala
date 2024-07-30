package nl.elisabeth.adventofcodeinscala

import scala.annotation.tailrec

object Day2_CubeConundrum {

  // PART 1 - if a bag of cubes contains a certain amount of red, green and blue cubes, which lines (games) in the data file
  // would be possible to get out of the bag. Take the sum of all those gameIds
  def sumGameIdsThatFitRealCubeCounts(filePath: os.Path,
                                      realCubeCounts: Map[String, Int]): Int =
    val lineStream: geny.Generator[String] = os.read.lines.stream(filePath)
    lineStream
      .map(line => line.split(": |; |, "))
      .filter(alternativeCubeNumbersWithinBounds(_, realCubeCounts))
      .map(getGameId)
      .foldLeft(0)(_ + _)

  @tailrec
  protected[adventofcodeinscala] def cubeNumbersWithinBounds(colourCountStrings: Array[String],
                                                             realCubeCounts: Map[String, Int]): Boolean =
    colourCountStrings.length match
      case 1 => true
      case _ =>
        val firstValue = colourCountStrings(1).trim.split(" ")
        if (firstValue(1) == "blue" && firstValue(0).toInt > realCubeCounts("blue")) return false
        else if (firstValue(1) == "red" && firstValue(0).toInt > realCubeCounts("red")) return false
        else if (firstValue(1) == "green" && firstValue(0).toInt > realCubeCounts("green")) return false
        cubeNumbersWithinBounds(colourCountStrings.tail, realCubeCounts)

  protected[adventofcodeinscala] def alternativeCubeNumbersWithinBounds(colourCountStrings: Array[String],
                                                                        realCubeCounts: Map[String, Int]): Boolean =
    colourCountStrings.tail.forall { entry =>
      val Array(countStr, cubeColour) = entry.trim.split(" ")
      val count = countStr.toInt
      realCubeCounts.get(cubeColour) match
        case Some(maxCount) => count <= maxCount
        case None => true
    }

  protected[adventofcodeinscala] def getGameId(line: Array[String]): Int =
    line(0).split(" ")(1).toInt

  // PART 2 - Per game (line) what is the fewest number of cubes of each color that could have been in the bag to make the game possible?
  // Take the power of these three numbers, and then the sum of all games
  protected[adventofcodeinscala] def sumProductOfMinimumCubesPerGame(filePath: os.Path): Int =
    val lineStream: geny.Generator[String] = os.read.lines.stream(filePath)
    lineStream
      .map(line => line.split(": |; |, "))
      .map(productOfMinimumCubes)
      .foldLeft(0)(_ + _)

  protected[adventofcodeinscala] def productOfMinimumCubes(line: Array[String]): Int =
    42

}

/* Takeaways
- Recursion is not always the way to go.
- Learning regex can be very helpful in dealing with text operations
- .forAll checks all instances for a certain condition and only returns true if they are all true
- .sum does not work directly on streams (only if you first convert it to a non-lazy collection like a sequence, list, or arrray)
 */
