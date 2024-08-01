package nl.elisabeth.adventofcodeinscala

import scala.annotation.tailrec

object Day1_Trebuchet {

  private val firstLetterOfAllDigits = "otfsen"
  private val lastLetterOfAllDigits = "eorxnt"
  private val wordToDigit = Map(
    "one" -> "1",
    "two" -> "2",
    "three" -> "3",
    "four" -> "4",
    "five" -> "5",
    "six" -> "6",
    "seven" -> "7",
    "eight" -> "8",
    "nine" -> "9"
  )

  def trebuchetPart1(lineStream: LazyList[String]): Int = sumOfCombinedFirstAndLastDigitOfEachLine(lineStream, firstAndLastDigit)
  def trebuchetPart2(lineStream: LazyList[String]): Int = sumOfCombinedFirstAndLastDigitOfEachLine(lineStream, firstAndLastDigitOrWrittenNumber)


 // combine the first digit and the last digit of each line of text, to form a single two digit number, then take the sum of all these numbers together
 // combineDigits can be added as a parameter since there are two ways; One that only checks literal digits and one that also checks numbers written out.
  def sumOfCombinedFirstAndLastDigitOfEachLine(lineStream: LazyList[String],
                                               combineDigits: String => Option[Int]): Int =
    lineStream
      .map(combineDigits)
      .filter(_.isDefined) // filter out the None
      .map(_.get) // take the values from the Options
      .fold(0)(_ + _) // take the sum of all the values generated in the stream


  // Functions for part 1
  @tailrec
  def firstAndLastDigit(line: String): Option[Int] =
    line.length match
      case 0 => None
      case _ =>
        if (line.head.isDigit) addLastDigitToFirst(line, line.head.toString)
        else firstAndLastDigit(line.tail)

  @tailrec
  private def addLastDigitToFirst(line: String, firstDigit: String): Option[Int] =
    line.length match
      case 0 => None
      case _ =>
        if (line.last.isDigit) Some(firstDigit.concat(line.last.toString).toInt)
        else addLastDigitToFirst(line.dropRight(1), firstDigit)


  // Functions for part 2
  @tailrec
  def firstAndLastDigitOrWrittenNumber(line: String): Option[Int] =
    line.length match
      case 0 => None
      case _ =>
        if (line.head.isDigit) addLastDigitOrWrittenNumberToFirst(line, line.head.toString)
        else if (firstLetterOfAllDigits.contains(line.head))
          if (line.length >= 5) firstWrittenDigitInString(line, 5)
          else if (line.length >= 4) firstWrittenDigitInString(line, 4)
          else if (line.length >= 3) firstWrittenDigitInString(line, 3)
          else firstAndLastDigitOrWrittenNumber(line.tail)
        else firstAndLastDigitOrWrittenNumber(line.tail)

  @tailrec
  private def addLastDigitOrWrittenNumberToFirst(line: String, firstDigit: String): Option[Int] =
    line.length match
      case 0 => None
      case _ =>
        if (line.last.isDigit) Some((firstDigit + line.last.toString).toInt)
        else if (lastLetterOfAllDigits.contains(line.last))
          if (line.length >= 5) lastWrittenDigitInString(line, firstDigit, 5)
          else if (line.length >= 4) lastWrittenDigitInString(line, firstDigit, 4)
          else if (line.length >= 3) lastWrittenDigitInString(line, firstDigit, 3)
          else addLastDigitOrWrittenNumberToFirst(line.dropRight(1), firstDigit)
        else addLastDigitOrWrittenNumberToFirst(line.dropRight(1), firstDigit)

  @tailrec
  private def firstWrittenDigitInString(line: String, digitWordLength: Int): Option[Int] =
    if (digitWordLength < 3) return firstAndLastDigitOrWrittenNumber(line.tail)
    wordToDigit.get(line.substring(0, digitWordLength)) match
      case Some(digit) => addLastDigitOrWrittenNumberToFirst(line.tail, digit)
      case None => firstWrittenDigitInString(line, digitWordLength - 1)

  @tailrec
  private def lastWrittenDigitInString(line: String, firstDigit: String, digitWordLength: Int): Option[Int] =
    if (digitWordLength < 3) return addLastDigitOrWrittenNumberToFirst(line.dropRight(1), firstDigit)
    wordToDigit.get(line.substring(line.length - digitWordLength, line.length)) match
      case Some(digit) => Some((firstDigit + digit).toInt)
      case None => lastWrittenDigitInString(line, firstDigit, digitWordLength - 1)

}


/*
Takeaways:
- create a Scala object instead of a class, so you don't have to make a new instance to call its methods.
- there are multiple ways to read text files. Since it's input, you cannot do that fully FP.
- You can use OS-Lib to read files. OS-Lib takes care of closing the file once the generator returned by stream is exhausted.
- You can apply head :: tail to Strings as well
- You can nest tail recursive functions (though it might not be very clean code)
- The substring method takes a range! (so last index number is not part of the selected elements)

After checking online answers:
- you can use the string operation ".find" that returns an option, same as your tailrec functions for part 1.
- REGEX is a good tool to work with these string searches and operations
*/
