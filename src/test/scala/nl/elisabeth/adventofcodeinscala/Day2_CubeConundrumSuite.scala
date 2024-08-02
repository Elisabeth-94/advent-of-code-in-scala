package nl.elisabeth.adventofcodeinscala

import nl.elisabeth.adventofcodeinscala.Day2_CubeConundrum.*
import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day2_CubeConundrumSuite extends AnyFunSuite:

  test("getGameId from string returns the number in the first element in an Array[String]") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    assert(getGameId(splitLine) == 30)
  }

  test("alternativeCubeNumbersWithinBounds. If any colour count is more than the real cube count return false") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    val realCubeCountsNoMatch = Map("red" -> 2, "blue" -> 5, "green" -> 4)
    val realCubeCountsExactMatch = Map("red" -> 7, "blue" -> 6, "green" -> 2)
    val realCubeCountsBroadMatch = Map("red" -> 8, "blue" -> 8, "green" -> 8)
    assert(!alternativeCubeNumbersWithinBounds(splitLine, realCubeCountsNoMatch))
    assert(alternativeCubeNumbersWithinBounds(splitLine, realCubeCountsExactMatch))
    assert(alternativeCubeNumbersWithinBounds(splitLine, realCubeCountsBroadMatch))
  }

  test("part1SumGameIdsThatFitRealCubeCounts on resource file should return 2512") {
    val inputStream = getClass.getResourceAsStream("/cube-conundrum.txt")
    val lines = Source.fromInputStream(inputStream).getLines() // datatype is iterator[String]
    val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None }
    assert(part1SumGameIdsThatFitRealCubeCounts(lazyLines) == 2512)
  }

  test("minimumCubesNeeded returns a map of the minimum amount of cubes needed per colour for a game (line) to be possible") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    assert(minimumCubesNeeded(splitLine) == Map("blue" -> 6, "green" -> 2, "red" -> 7))
  }

  test("alternativeMinimumCubesNeeded returns a map of the minimum amount of cubes needed per colour for a game (line) to be possible") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    assert(alternativeMinimumCubesNeeded(splitLine) == Map("blue" -> 6, "green" -> 2, "red" -> 7))
  }

  test("part2SumProductOfMinimumCubesPerGame on resource file should return 67335") {
    val inputStream = getClass.getResourceAsStream("/cube-conundrum.txt")
    val lines = Source.fromInputStream(inputStream).getLines() // datatype is iterator[String]
    val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None }
    assert(part2SumProductOfMinimumCubesPerGame(lazyLines) == 67335)
  }
