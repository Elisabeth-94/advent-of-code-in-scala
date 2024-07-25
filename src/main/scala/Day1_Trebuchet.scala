import scala.io.Source
import scala.util.{Try, Success, Failure}

object Day1_Trebuchet {

  @main def trebuchet(): Unit =
    println("This is the Trebuchet exercise")
    // combine the first digit and the last digit of each line of text, to form a single two digit number
    // then take the sum of all these numbers together

    println(s"Current working directory: ${os.pwd}")

// Scala docs way to read a file:
    val root = os.pwd // gets the current working directory
    val filePath: os.Path = root / "src" / "main" / "resources" / "trebuchet.txt"
    val lineStream: geny.Generator[String] = os.read.lines.stream(filePath) // OS-Lib takes care of closing the file once the generator returned by stream is exhausted.
    // now try to use this stream (similar to lazy list) to get the numbers per line
    lineStream
      .filter(line => line.nonEmpty && line.charAt(0).isDigit)
      .map(line => line.charAt(0))
      .foreach(d => println(d))

//// ChatGPTs way to read a file conform FP
//    val filename = "/trebuchet.txt"
//    val lines = readResourceFileLinesIntoList(filename)
//
//    lines match {
//      case Some(linesList) =>
//        linesList.foreach(println) // Process each line individually. Add a function that gets the numbers
//      case None =>
//        println(s"Resource $filename not found or could not be read.")
//    }
//
//  // Function to read file content and return it as an Option[List[String]]
//  def readResourceFileLinesIntoList(filename: String): Option[List[String]] =
//    val resourceStream = Option(getClass.getResourceAsStream(filename))
//    resourceStream.flatMap { // you use curly braces here instead of parentheses, because you have a multi-line expression
//      stream =>
//      Try(Source.fromInputStream(stream).getLines().toList) match {
//        case Success(lines) =>
//          // Ensure the source is closed after reading
//          stream.close()
//          Some(lines)
//        case Failure(_) =>
//          None
//      }
//    }

}
