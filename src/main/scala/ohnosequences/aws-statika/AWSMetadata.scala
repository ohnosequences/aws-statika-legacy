package ohnosequences.statika.aws

import ohnosequences.statika._

/*

### AWS-specific bundle metadata

Here we declare only Amazon-specific metadata

*/


trait AWSMetadata {

  // Amazon instance profile ARN corresponding to the role with credentials (for resolving)
  val instanceProfileARN: Option[String]

}

trait AWSMetadataOf[S] extends MetadataOf[S] with AWSMetadata 
