package ohnosequences.statika.aws

import ohnosequences.statika._

trait AnyAWSDistribution extends AnyDistribution {

  /*  ### Metadata

      Metadata contains deployment specific information. See [Metadata source](Metadata.md).
  */
  type Metadata <: AnyMetadata
  val metadata: Metadata


  /*  ### AMI dependency

      We have a generic `AbstractAMI` type and every AWS distribution contains an 
      instance of it as all the members/bundles of the distribution are supposed 
      to be installed in the same environment.
  */
  type AMI <: AbstractAMI
  val ami: AMI

  def userScript[
      M >: Metadata <: ami.MetadataBound
    , B <: AnyBundle : isMember : isInstallable
    ](b: B, creds: AWSCredentials = RoleCredentials) = 
    ami.userScript[M](metadata, this.fullName, b.fullName, creds)


  // Initial check, that we are running on the right AMI  
  def setContext: InstallResults = ami.checkID

}


/* Constructor with AMI and sets of members/deps as type-parameters */
abstract class AWSDistribution[
    Ami <: AbstractAMI
  , MD  <: Ami#MetadataBound
  , Ms  <: TypeSet : ofBundles
  , Ds  <: TypeSet : ofBundles
  , Tw  <: HList   : towerFor[Ds]#is
  ](val metadata: MD
  , val ami: Ami
  , val members: Ms
  , deps: Ds = ∅
  ) extends Bundle[Ds, Tw](deps) with AnyAWSDistribution {

  type Metadata = MD
  type AMI = Ami
  type Members = Ms 

}
