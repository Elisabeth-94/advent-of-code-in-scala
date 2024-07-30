package nl.elisabeth.adventofcodeinscala

import nl.elisabeth.adventofcodeinscala.Day2_CubeConundrum.*

class Day2_CubeConundrumTest extends munit.FunSuite:

  test("getGameId from string. 'Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red' returns 30") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    assertEquals(getGameId(splitLine), 30)
  }

  test("cubeNumbersWithinBounds. If any colour count is over the real cube count return false") {
    val splitLine = "Game 30: 5 red, 3 blue; 2 red; 2 green, 6 blue, 7 red; 5 red".split(": |; |, ")
    val realCubeCountsNoMatch = Map("red" -> 2, "blue" -> 5, "green" -> 4)
    val realCubeCountsExactMatch = Map("red" -> 7, "blue" -> 6, "green" -> 2)
    val realCubeCountsBroadMatch = Map("red" -> 8, "blue" -> 8, "green" -> 8)
    assertEquals(cubeNumbersWithinBounds(splitLine, realCubeCountsNoMatch), false)
    assertEquals(cubeNumbersWithinBounds(splitLine, realCubeCountsExactMatch), true)
    assertEquals(cubeNumbersWithinBounds(splitLine, realCubeCountsBroadMatch), true)
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
    val root = os.pwd // gets the current working directory
    val filePathCubeConundrum: os.Path = root / "src" / "main" / "resources" / "cube-conundrum.txt"
    val realCubeCounts = Map("green" -> 13, "red" -> 12, "blue" -> 14)
    assertEquals(sumGameIdsThatFitRealCubeCounts(filePathCubeConundrum, realCubeCounts), 2512)
  }