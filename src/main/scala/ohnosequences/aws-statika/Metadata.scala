/*
## Metadata

We want to map distribution to it's artifact. For example, with the generated information from SBT.
*/

package ohnosequences.statika.aws

trait AnyMetadata


/* ### SBT-specific metadata */
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

