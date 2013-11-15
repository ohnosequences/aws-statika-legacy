Nice.scalaProject

name := "aws-statika"

description := "aws-statika project"

organization := "ohnosequences"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
    "ohnosequences" %% "statika" % "1.0.0-RC2"
  , "org.scalatest" %% "scalatest" % "1.9.2" % "test"
  )
