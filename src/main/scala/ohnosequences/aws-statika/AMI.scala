package ohnosequences.statika.aws

import ohnosequences.statika._

/* 

### Amazon Machine Images (AMIs)

This abstraction represents AMIs, that are supposed to be used in distributions
to control, that all the member of it are installed with the same image.

*/

abstract class AbstractAMI(val id: String, val amiVersion: String) {

  import scala.sys.process._
  import java.io._
  import java.net.URL

  /*
  This method checks that the machine on which it's called has
  the corresponding image.
  */
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

  /*
  This is the main purpose of having this image abstraction:
  to be able to generate a user-script for a particular bundle,
  using which one can launch an instance or an auto-scaling group
  with this bundle being installed (what is called to _apply a bundle_).
  - it requires bundle's metadata and a distribution, because it 
    needs to fill this placeholders in the application project
  - credentials can be either set manually, or retrieved from an s3 bucket
  */
  def userScript[
      D <: AnyDistribution
    , B <: AnyBundle : distribution.isMember : distribution.isInstallable
    ](distribution: D
    , bundle: B
    , credentials: AWSCredentials = RoleCredentials
    ): String

}
