package nl.elisabeth.adventofcodeinscala

import nl.elisabeth.adventofcodeinscala.Day1_Trebuchet.*

import scala.io.Source

class Day1_TrebuchetTest extends munit.FunSuite:

    test("4hello2-int-42") {
      assertEquals(firstAndLastDigit("4hello2"), Some(42))
    }

    test("'7' should return an Option with value 77") {
      assertEquals(firstAndLastDigit("7"), Some(77))
    }

    test("trebuchet part 1 on given data file should return 54338") {
      val inputStream = getClass.getResourceAsStream("/trebuchet.txt")
      val lines = Source.fromInputStream(inputStream).getLines() // datatype is iterator[String]
      val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None}
      assertEquals(sumOfCombinedFirstAndLastDigitOfEachLine(lazyLines, firstAndLastDigit), 54338)
    }

    test("trebuchet part 2 on given data file should return 53389") {
      val inputStream = getClass.getResourceAsStream("/trebuchet.txt")
      val lines = Source.fromInputStream(inputStream).getLines() // datatype is iterator[String]
      val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None }
      assertEquals(sumOfCombinedFirstAndLastDigitOfEachLine(lazyLines, firstAndLastDigitOrWrittenNumber), 53389)
    }

    test("firstDigitWithLastDigitInString - 'two1nine' should return an Option with value 2") {
      assertEquals(firstAndLastDigitOrWrittenNumber("4hello2"), Some(42))
      assertEquals(firstAndLastDigitOrWrittenNumber("7"), Some(77))
      assertEquals(firstAndLastDigitOrWrittenNumber("two1nine"), Some(29))
      assertEquals(firstAndLastDigitOrWrittenNumber("eightwothree"), Some(83))
      assertEquals(firstAndLastDigitOrWrittenNumber("abcone2threexyz"), Some(13))
      assertEquals(firstAndLastDigitOrWrittenNumber("xtwone3four"), Some(24))
      assertEquals(firstAndLastDigitOrWrittenNumber("4nineeightseven2"), Some(42))
      assertEquals(firstAndLastDigitOrWrittenNumber("zoneight234"), Some(14))
      assertEquals(firstAndLastDigitOrWrittenNumber("7pqrstsixteen"), Some(76))
    }


