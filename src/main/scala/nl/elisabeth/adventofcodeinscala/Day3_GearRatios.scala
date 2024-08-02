package nl.elisabeth.adventofcodeinscala
import scala.annotation.tailrec

object Day3_GearRatios {

  // case class validNumber(value: Int, startCoord: Array[Int], endCoord: Array[Int])

  def sumNumbersWithNeighbourSymbolAlt(lazyLines: LazyList[String]): Int =
      lazyLines
        .sliding(3)
        .flatMap(processThreeLines)
        .sum

  private def processThreeLines(lines: Seq[String]): List[Int] =
    processThreeLinesHelper(lines, List())

  // Nested tail recursion to go over every element of the 2d sequence
  @tailrec
  private def processThreeLinesHelper(lines: Seq[String], accumulator: List[Int]): List[Int] =
    lines.length match
      case 0 => accumulator
      case _ => processThreeLinesHelper(lines, processLine(lines, lines.head, accumulator))

  // TODO: how do you retain the indices?
  @tailrec
  private def processLine(lines: Seq[String], line: String, accumulator: List[Int]): List[Int] =
    line.length match
      case 0 => accumulator
      case _ =>
        if (line(0).isDigit)
          if (line(1).isDigit)
            if (line(2).isDigit) processLine(lines, line.tail, accumulator)// check surrounding positions of 3 digit number
            else processLine(lines, line.tail, accumulator)// check surrounding positions of 2 digit number
          else processLine(lines, line.tail, accumulator)// check surrounding positions of 1 digit number
        else processLine(lines, line.tail, accumulator)

  // TODO: check if any of the surrounding spots do not contain a period or number
  private def checkSurroundingCharacters(lines: Seq[String], indices: Array[Int], digitCountOfNumber: Int): Boolean =
    if (digitCountOfNumber == 1) true
    else true






  // find numbers in line (1 to 3 digits long)
  // check their surroundings: if any spot in the block around a number is not a period or another number, it's valid
  // return valid numbers (list per line?)

}

/*
Takeaways
 */
