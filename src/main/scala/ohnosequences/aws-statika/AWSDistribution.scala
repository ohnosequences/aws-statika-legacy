package ohnosequences.statika.aws

import ohnosequences.statika._

trait AnyAWSDistribution extends AnyDistribution {

  /*
    #### AMI dependency

    We have a generic `AbstractAMI` type and a distribution contains an 
    instance of it as all the members/bundles of the distribution are supposed 
    to be installed in the same environment, so we need to know that they work 
    with a particular image.
  */
  type AMI <: AbstractAMI
  val ami: AMI

  def userScript[
      B <: AnyBundle : IsMember
    , Bs <: HList : (b.FlatDeps :+ B)#is : Installable
    ](b: B, credentials: AWSCredentials = RoleCredentials): String =
      ami.userScript(this, b, credentials)

  // Amazon instance profile ARN corresponding to the role with credentials (for resolving)
  val instanceProfileARN: Option[String] = None


  /*
    #### Resources management
  */
  val resourceBucket: Path = ""

  /*
  This method checks that the machine on which it's called has the corresponding 
  instance profile (which is set in the distribution's metadata).
  */
  // def checkRole: InstallResults = {
  //   try {
  //     val currentIdURL = new URL("http://169.254.169.254/latest/meta-data/iam/info")
  // TODO: here some parsing is needed, because the response is in json format
  //     val profile = io.Source.fromURL(currentIdURL).mkString
  //     if (profile == metadata.instanceProfileARN)
  //       success("Checked that instance profile ARN is " + profile)
  //     else
  //       failure("AMI should be "+ metadata.instanceProfileARN +", instead of "+ profile)
  //   } catch {
  //     case t : Throwable => failure("Couldn't check instance profile ARN because of "+t.toString)
  //   }
  // }

  def setContext: InstallResults = ami.checkID
}

// Just a constructor with AMI and lists of members/deps as type-parameters  
abstract class AWSDistribution[
    A <: AbstractAMI
  , Ms <: HList : ofBundles
  , Ds <: HList : ofBundles
  , FD <: HList : Flat[Ds]#is
  ](val ami: A
  , val members: Ms
  , val deps: Ds = HNil : HNil
  ) extends AnyAWSDistribution {

  type AMI = A
  type Members = Ms 
  type Deps = Ds
  type FlatDeps = FD
  val flatDeps = flatten(deps)

}
