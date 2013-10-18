import ohnosequences.sbt._

Era7.allSettings

name := "aws-statika"

description := "aws-statika project"

homepage := Some(url("https://github.com/ohnosequences/aws-statika"))

organization := "ohnosequences"

organizationHomepage := Some(url("http://ohnosequences.com"))

licenses := Seq("AGPLv3" -> url("http://www.gnu.org/licenses/agpl-3.0.txt"))

scalaVersion := "2.10.3"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
    "ohnosequences" %% "statika" % "0.17.0"
  , "org.scalatest" %% "scalatest" % "1.9.2" % "test"
  , "ohnosequences" %% "literator" % "0.2.0" % "test"
  )

scalacOptions ++= Seq(
    "-feature"
  , "-language:higherKinds"
  , "-language:implicitConversions"
  , "-language:postfixOps"
  , "-deprecation"
  , "-unchecked"
  )
