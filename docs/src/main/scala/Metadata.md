### Index

+ src
  + main
    + scala
      + [AMI.scala](AMI.md)
      + [AWSCredentials.scala](AWSCredentials.md)
      + [AWSDistribution.scala](AWSDistribution.md)
      + [Metadata.scala](Metadata.md)
  + test
    + scala
      + [AMITest.scala](../../test/scala/AMITest.md)

------

## Metadata

We want to map distribution to it's artifact. For example, with the generated information from SBT.

See also [sbt-statika](https://github.com/ohnosequences/sbt-statika/) sbt plugin, which accumulates
for convenience all the common sbt settings, needed by bundles. Moreover, it provides a (compile-
time) generated metadata for the project (of `SbtMetadata` type defined here).


```scala
package ohnosequences.statika.aws

trait AnyMetadata
```

### SBT-specific metadata

```scala
trait SbtMetadata extends AnyMetadata {
  // In ivy terminology:
  // groupID
  val organization: String
  // artifactID
  val artifact: String
  // revision
  val version: String

  def moduleID = "\""+organization+"\" %% \""+artifact+"\" % \""+version+"\""
  override def toString = moduleID

  // serialized sbt resolvers, which are needed to build the project
  val resolvers: Seq[String]
  val privateResolvers: Seq[String]
}
```

### Metadata for deployment with a fat jar artifact

```scala
trait FatJarMetadata extends AnyMetadata {
  val artifactUrl: String
}

```

