package nl.elisabeth.adventofcodeinscala

import nl.elisabeth.adventofcodeinscala.Day2_CubeConundrum.*

import scala.io.Source

class Day2_CubeConundrumTest extends munit.FunSuite:

  test("getGameId from string. 'Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red' returns 30") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    assertEquals(getGameId(splitLine), 30)
  }

  test("alternativeCubeNumbersWithinBounds. If any colour count is over the real cube count return false") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    val realCubeCountsNoMatch = Map("red" -> 2, "blue" -> 5, "green" -> 4)
    val realCubeCountsExactMatch = Map("red" -> 7, "blue" -> 6, "green" -> 2)
    val realCubeCountsBroadMatch = Map("red" -> 8, "blue" -> 8, "green" -> 8)
    assertEquals(alternativeCubeNumbersWithinBounds(splitLine, realCubeCountsNoMatch), false)
    assertEquals(alternativeCubeNumbersWithinBounds(splitLine, realCubeCountsExactMatch), true)
    assertEquals(alternativeCubeNumbersWithinBounds(splitLine, realCubeCountsBroadMatch), true)
  }

  test("cubeConundrum part 1 on given data file should return 2512") {
    val inputStream = getClass.getResourceAsStream("/cube-conundrum.txt")
    val lines = Source.fromInputStream(inputStream).getLines() // datatype is iterator[String]
    val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None }
    assertEquals(part1SumGameIdsThatFitRealCubeCounts(lazyLines), 2512)
  }

  test("minimumCubesNeeded returns a map of the minimum amount of cubes needed per colour for a game (line) to be possible") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    assertEquals(minimumCubesNeeded(splitLine), Map("blue" -> 6, "green" -> 2, "red" -> 7))
  }

  test("alternativeMinimumCubesNeeded returns a map of the minimum amount of cubes needed per colour for a game (line) to be possible") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    assertEquals(alternativeMinimumCubesNeeded(splitLine), Map("blue" -> 6, "green" -> 2, "red" -> 7))
  }

  test("cubeConundrum part 2 on given data file should return 67335") {
    val inputStream = getClass.getResourceAsStream("/cube-conundrum.txt")
    val lines = Source.fromInputStream(inputStream).getLines() // datatype is iterator[String]
    val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None }
    assertEquals(part2SumProductOfMinimumCubesPerGame(lazyLines), 67335)
  }
