/*
## Metadata

We want to map distribution to it's artifact. For example, with the generated information from SBT.
*/

package ohnosequences.statika.aws

/* ### Generic type dependent meta data */
trait Metadata {
  // type Subj
}

// trait MetadataOf[S] extends Metadata { type Subj = S }


/*
### SBT-specific metadata 

Mainly moduleID and resolvers.
*/
trait SbtMetadata extends Metadata {
  // In ivy terminology:
  // groupID
  val organization: String
  // artifactID
  val artifact: String
  // revision
  val version: String

  def moduleID = "\""+organization+"\" %% \""+artifact+"\" % \""+version+"\""

  override def toString = moduleID

  // statika version used for building `Subj` project
  val statikaVersion: String

  // sbt resolvers, which are needed to build `Subj` project
  val resolvers: Seq[String]
  val privateResolvers: Seq[String]
}

