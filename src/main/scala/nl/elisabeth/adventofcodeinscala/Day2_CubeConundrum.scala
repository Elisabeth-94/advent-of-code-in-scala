package nl.elisabeth.adventofcodeinscala

import scala.annotation.tailrec

object Day2_CubeConundrum {

  // PART 1 - if a bag of cubes contains a certain amount of red, green and blue cubes, which lines (games) in the data file
  // would be possible to get out of the bag. Take the sum of all those gameIds
  def sumGameIdsThatFitRealCubeCounts(lineStream: geny.Generator[String],
                                      realCubeCounts: Map[String, Int]): Int =
    lineStream
      .map(line => line.split(": |; |, "))
      .filter(alternativeCubeNumbersWithinBounds(_, realCubeCounts))
      .map(getGameId)
      .foldLeft(0)(_ + _)

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
  protected[adventofcodeinscala] def sumProductOfMinimumCubesPerGame(lineStream: geny.Generator[String]): Int =
    lineStream
      .map(line => line.split(": |; |, "))
      .map(minimumCubesNeeded)
      .map(dict => dict.values.product)
      .foldLeft(0)(_ + _)

  protected[adventofcodeinscala] def minimumCubesNeeded(line: Array[String]): Map[String, Int] =
    val cubeCounter = Map("blue" -> 0, "green" -> 0, "red" -> 0)
    minimumCubesNeededHelper(line, cubeCounter)

  @tailrec
  private def minimumCubesNeededHelper(line: Array[String], cubeCounter: Map[String, Int]): Map[String, Int] =
    line.length match
      case 1 => cubeCounter
      case _ =>
        val Array(countStr, colourKey) =  line(1).split(" ")
        cubeCounter.get(colourKey) match
          case Some(value) =>
            if (value < countStr.toInt) minimumCubesNeededHelper(line.tail, cubeCounter.updated(colourKey, countStr.toInt))
            else minimumCubesNeededHelper(line.tail, cubeCounter)
          case None => minimumCubesNeededHelper(line.tail, cubeCounter)

  // without recursion
  protected[adventofcodeinscala] def alternativeMinimumCubesNeeded(line: Array[String]): Map[String, Int] =
    line.drop(1).foldLeft(Map("blue" -> 0, "green" -> 0, "red" -> 0)) { 
      (cubeCounter, entry) =>
      val Array(countStr, colourKey) = entry.split(" ")
      val count = countStr.toInt
      cubeCounter.updatedWith(colourKey) {
        case Some(value) if value < count => Some(count)
        case other => other
      }
    }
}

/* Takeaways
- Recursion is not always the way to go.
- Learning regex can be very helpful in dealing with text operations
- .forAll checks all instances for a certain condition and only returns true if they are all true
- .sum does not work directly on streams (only if you first convert it to a non-lazy collection like a sequence, list, or arrray)
- don't forget you can use foldLeft on arrays as well.
 */
