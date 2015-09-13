
name := "crowleypool"

version := "1.0.0.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.4.0",
  "net.codingwell" %% "scala-guice" % "4.0.0",
  "org.squeryl" % "squeryl_2.11" % "0.9.5-7",
  "net.sourceforge.jtds" % "jtds" % "1.2.4",
  cache,
  filters
)

lazy val crowleypool = (project in file(".")).enablePlugins(PlayScala)

routesGenerator := InjectedRoutesGenerator

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen" // Warn when numerics are widened.
)