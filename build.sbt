name := "CQRS"

version := "0.1"

scalaVersion := "2.12.6"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-slick" % "3.0.2",
  "com.h2database"    % "h2" % "1.4.196" % Runtime,
  specs2              % Test
)
