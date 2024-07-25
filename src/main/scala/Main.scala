@main def completeAdvent(args: String*): Unit =

  val argsList = args.toList

  println("Hello world!" + argsList(0))
  println(msg)

def msg = "I was compiled by Scala 3. :)"
