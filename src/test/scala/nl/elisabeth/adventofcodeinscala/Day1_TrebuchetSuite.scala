package nl.elisabeth.adventofcodeinscala

import nl.elisabeth.adventofcodeinscala.Day1_Trebuchet.*
import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day1_TrebuchetSuite extends AnyFunSuite:

    test("firstAndLastDigit input '4hello2-int-42' should return an Option with 42") {
      assert(firstAndLastDigit("4hello2").contains(42))
    }

    test("firstAndLastDigit input '7' should return an Option with value 77") {
      assert(firstAndLastDigit("7").contains(77))
    }

    test("sumOfCombinedFirstAndLastDigitOfEachLine on resources file should return 54338") {
      val inputStream = getClass.getResourceAsStream("/trebuchet.txt")
      val lines = Source.fromInputStream(inputStream).getLines() // datatype is iterator[String]
      val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None}
      assert(sumOfCombinedFirstAndLastDigitOfEachLine(lazyLines, firstAndLastDigit) == 54338)
    }

    test("sumOfCombinedFirstAndLastDigitOfEachLine on resources file with written numbers included should return 53389") {
      val inputStream = getClass.getResourceAsStream("/trebuchet.txt")
      val lines = Source.fromInputStream(inputStream).getLines() // datatype is iterator[String]
      val lazyLines: LazyList[String] = LazyList.unfold(lines) { it => if (it.hasNext) Some((it.next(), it)) else None }
      assert(sumOfCombinedFirstAndLastDigitOfEachLine(lazyLines, firstAndLastDigitOrWrittenNumber) == 53389)
    }

    test("firstDigitWithLastDigitInString (non-recursive version of firstAndLastDigit) should return a two-digit number") {
      assert(firstAndLastDigitOrWrittenNumber("4hello2").contains(42))
      assert(firstAndLastDigitOrWrittenNumber("7").contains(77))
      assert(firstAndLastDigitOrWrittenNumber("two1nine").contains(29))
      assert(firstAndLastDigitOrWrittenNumber("eightwothree").contains(83))
      assert(firstAndLastDigitOrWrittenNumber("abcone2threexyz").contains(13))
      assert(firstAndLastDigitOrWrittenNumber("xtwone3four").contains(24))
      assert(firstAndLastDigitOrWrittenNumber("4nineeightseven2").contains(42))
      assert(firstAndLastDigitOrWrittenNumber("zoneight234").contains(14))
      assert(firstAndLastDigitOrWrittenNumber("7pqrstsixteen").contains(76))
    }


