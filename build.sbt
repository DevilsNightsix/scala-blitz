name := """blitz"""
organization := "org.dvnsx"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += evolutions
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "org.dvnsx.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "org.dvnsx.binders._"
