/*
### Amazon Machine Images (AMIs)

This abstraction represents AMIs, that are supposed to be used in distributions
to control, that all the members are installed with the same image.
*/

package ohnosequences.statika.aws

import ohnosequences.statika._

abstract class AbstractAMI(val id: String, val amiVersion: String) {

  import java.io._
  import java.net.URL

  /* This method checks that the machine on which it's called has the corresponding image. */
  def checkID: InstallResults = {
    try {
      val currentIdURL = new URL("http://169.254.169.254/latest/meta-data/ami-id")
      val ami = io.Source.fromURL(currentIdURL).mkString
      if (ami == id)
        success("Checked that Amazon Machine Image is " + id)
      else
        failure("AMI should be "+ id +", instead of "+ ami)
    } catch {
      case t : Throwable => failure("Couldn't check AMI id because of "+t.toString)
    }
  }

  /*  This is the main purpose of having this image abstraction: to be able to generate a 
      user-script for a particular bundle, using which can launch an instance or an 
      auto-scaling group with this bundle being installed (what is called to _apply a bundle_).
      - it requires a bundle and a distribution, or more precisely their metadata, because it 
        needs to fill placeholders in the project of the so called "bundle applicator" project;
      - the given bundle must be a member of the given distribution and must have all the 
        necessary implicits for being installed with it;
      - for info about credentials see the definition of `AWSCredentials` type;
  */
  def userScript[
      D <: AnyDistribution
    , B <: AnyBundle : distribution.isMember : distribution.isInstallable
    ](distribution: D
    , bundle: B
    , credentials: AWSCredentials = RoleCredentials
    ): String

}
