name := "poly-scala-jackson"

version := "0.1"

scalaVersion := "2.10.1"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.2.3"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"


