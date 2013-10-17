## Metadata

We want to map distribution to it's artifact. For example, with the generated information from SBT.


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

  // statika version used for building the project
  val statikaVersion: String

  // sbt resolvers, which are needed to build the project
  val resolvers: Seq[String]
  val privateResolvers: Seq[String]
}


```