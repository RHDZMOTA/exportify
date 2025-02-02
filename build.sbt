import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.rhdzmota",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "exportify",

    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.seleniumhq.selenium" % "selenium-java" % "2.3.1"
    )
  )
