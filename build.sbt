import sbtrelease._

name := "aws-statika"

description := "aws-statika project"

homepage := Some(url("https://github.com/ohnosequences/aws-statika"))

organization := "ohnosequences"

organizationHomepage := Some(url("http://ohnosequences.com"))

licenses := Seq("AGPLv3" -> url("http://www.gnu.org/licenses/agpl-3.0.txt"))


scalaVersion := "2.10.3"

publishMavenStyle := true

// for publishing you need to set `s3credentials`
publishTo <<= (isSnapshot, s3credentials) { 
                (snapshot,   credentials) => 
  val prefix = if (snapshot) "snapshots" else "releases"
  credentials map S3Resolver(
      "Era7 "+prefix+" S3 bucket"
    , "s3://"+prefix+".era7.com"
    ).toSbtResolver
}


resolvers ++= Seq ( 
    "Era7 Releases"  at "http://releases.era7.com.s3.amazonaws.com"
  , "Era7 Snapshots" at "http://snapshots.era7.com.s3.amazonaws.com"
  )

libraryDependencies ++= Seq(
    "ohnosequences" %% "statika" % "0.17.0-SNAPSHOT"
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


// sbt-release settings
releaseSettings
