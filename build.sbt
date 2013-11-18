Nice.scalaProject

name := "aws-statika"

description := "aws-statika project"

organization := "ohnosequences"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
    "ohnosequences" %% "statika" % "1.0.0"
  , "org.scalatest" %% "scalatest" % "2.0" % "test"
  )
