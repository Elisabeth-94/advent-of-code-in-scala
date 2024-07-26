import scala.annotation.tailrec

object Day1_Trebuchet {
  
  // combine the first digit and the last digit of each line of text, to form a single two digit number, then take the sum of all these numbers together
  def part1(filePath: os.Path): Int =
    val lineStream: geny.Generator[String] = os.read.lines.stream(filePath)
    lineStream
      .map(firstDigitCombinedWithLastDigitInString)
      .filter(_.isDefined) // filter out the None
      .map(_.get) // take the values from the Options
      .fold(0)(_ + _) // take the sum of all the values generated in the stream
    
  def part2(filePath: os.Path): Int =
    // TODO
    40 + 2
      
      
  @tailrec
  def firstDigitCombinedWithLastDigitInString(line: String): Option[Int] =
    line.length match
      case 0 => None
      case _ =>
        if (line.head.isDigit) combineWithlastDigitInString(line, line.head.toString)
        else firstDigitCombinedWithLastDigitInString(line.tail)

  @tailrec
  def combineWithlastDigitInString(line: String, firstDigit: String): Option[Int] =
    line.length match
      case 0 => None
      case _ =>
        if (line.last.isDigit) Some(firstDigit.concat(line.last.toString).toInt)
        else combineWithlastDigitInString(line.dropRight(1), firstDigit)

  /* Takeaways:
  - create a Scala object instead of a class, so you don't have to make a new instance to call its methods.
  - there are multiple ways to read text files. Since it's input, you cannot do that fully FP.
  - You can use OS-Lib to read files. OS-Lib takes care of closing the file once the generator returned by stream is exhausted.
  - You can apply head :: tail to Strings as well
  - You can nest tail recursive functions (though it might not be very clean code)
  */
}
