package marine

import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

/**
 * SBT file for 'marine-weather' / 'marine-api' / 'marine-towns'
 */
object AkkaExamplesBuild extends Build {

  // Versions ( Add only grouped versions here )
  val akkaVersion = "2.3.4"
  val kamonVersion = "0.3.1"
  val sprayVersion = "1.3.1"
  val slf4jVersion = "1.6.6"

  // Group for common testing dependencies
  val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % "2.2.0" % "test",
    "org.mockito" % "mockito-all" % "1.9.5" % "test",
    "org.powermock" % "powermock-core" % "1.5.2" % "test",
    "org.powermock" % "powermock-module-junit4" % "1.5.2" % "test",
    "org.powermock" % "powermock-api-mockito" % "1.5.2" % "test",
    "com.lordofthejars" % "nosqlunit-mongodb" % "0.7.7" % "test",
    "org.easytesting" % "fest-assert-core" % "2.0M10" % "test",
    "junit" % "junit" % "4.11" % "test"
  )

  // Group for akka.io
  val akkaDependencies = List(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
  )

  val loggingDependencies = List(
    "org.slf4j" % "slf4j-jcl" % "1.7.5",
    "org.slf4j" % "slf4j-api" % "1.7.5",
    "org.slf4j" % "slf4j-log4j12" % "1.7.5",
    "ch.qos.logback" % "logback-classic" % "1.0.9"
  )

  // Group for spray.io
  val sprayDependencies = List(
    "io.spray" % "spray-client" % sprayVersion,
    "io.spray" % "spray-routing" % sprayVersion,
    "io.spray" % "spray-can" % sprayVersion,
    "io.spray" % "spray-caching" % sprayVersion
  )

  // Group for kamon.io
  val kamonDependencies = List(
    "io.kamon" %% "kamon-core" % kamonVersion,
    "io.kamon" %% "kamon-statsd" % kamonVersion,
    "io.kamon" %% "kamon-spray" % kamonVersion
  )

  //removed: ++ assemblySettings ++ scalariformSettings
  val sharedSettings = Project.defaultSettings ++ assemblySettings ++  Seq(
    organization := "justexamples",
    scalaVersion := "2.10.4",
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    resolvers ++= Seq(
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    )
  )

  //-------------- Main Project & 3 modules ----------------//
  lazy val akkaExamplesproject = Project(
    id = "akka",
    base = file("."),
    settings = sharedSettings
  ).aggregate(
      day1
  )
  lazy val day1 = module("day1").settings(
    libraryDependencies ++= testDependencies ++ akkaDependencies ++ kamonDependencies ++ testDependencies ++ loggingDependencies ++
      Seq(
      "io.spray" %% "spray-json" % "1.2.6",
      // To remove
      "com.typesafe" % "config" % "1.2.1",
      "com.typesafe" %% "scalalogging-slf4j" % "1.1.0",
      "ch.qos.logback" % "logback-classic" % "1.1.1",
      "com.javadocmd" % "simplelatlng" % "1.3.0",
      "net.sf.trove4j" % "trove4j" % "2.0.2"
    )
  )
  lazy val day2 = module("day2").settings(
    libraryDependencies ++= testDependencies ++ akkaDependencies ++ kamonDependencies ++ testDependencies ++ loggingDependencies ++
      Seq(
        "io.spray" %% "spray-json" % "1.2.6",
        "org.json4s" %% "json4s-native" % "3.2.10",
        // To remove
        "com.google.code.gson" % "gson" % "2.2.4",
        "org.perf4j" % "perf4j" % "0.9.16",
        "com.typesafe" % "config" % "1.2.1",
        "com.typesafe" %% "scalalogging-slf4j" % "1.1.0",
        "ch.qos.logback" % "logback-classic" % "1.1.1",
        "com.javadocmd" % "simplelatlng" % "1.3.0",
        "net.sf.trove4j" % "trove4j" % "2.0.2",
        "org.mongodb" %% "casbah" % "2.7.2"
      )
  )

  // Wrapper method
  def module(name: String) = {
    val newid = "akka-%s".format(name)
    Project(
      id = newid,
      base = file(newid),
      settings = sharedSettings ++ Seq(Keys.name := newid)
    )
  }

}
