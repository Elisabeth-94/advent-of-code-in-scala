val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "advent-of-code-in-scala",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq("org.scalameta" %% "munit" % "1.0.0" % Test,     // This enables you to work with MUnit for your Unit tests
                        "com.lihaoyi" %% "os-lib" % "0.10.1") // this enables you to work with Scala's OS-lib (file reading and writing)
  )
