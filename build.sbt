val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "advent-of-code-in-scala",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    organization := "nl.elisabeth",
    description := "A project for solving Advent of Code challenges in Scala.",

    libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.2.18" % Test) // This enables you to work with MUnit for your Unit tests
  )
