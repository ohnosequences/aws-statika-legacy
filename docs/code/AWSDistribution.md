## Abstract AWS distributions

```scala
package ohnosequences.statika.aws

import ohnosequences.statika._

trait AnyAWSDistribution extends AnyDistribution {
```

### AMI dependency

We have a generic `AbstractAMI` type and every AWS distribution contains an 
instance of it as all the members/bundles of the distribution are supposed 
to be installed in the same environment.

```scala
  type AMI <: AbstractAMI
  val ami: AMI
```

### Metadata

Metadata contains deployment specific information. See [Metadata source](Metadata.md).

```scala
  val metadata: ami.Metadata


  def userScript[B <: AnyBundle : isMember]
    (b: B, creds: AWSCredentials = RoleCredentials) =
      ami.userScript(metadata, this.fullName, b.fullName, creds)


  // Initial check, that we are running on the right AMI  
  def setContext: InstallResults = ami.checkID

}
```

Constructor with AMI and sets of members/deps as type-parameters

```scala
abstract class AWSDistribution[
    Ami <: AbstractAMI
  , Ms  <: TypeSet : ofBundles
  , Ds  <: TypeSet : ofBundles
  , Tw  <: HList   : towerFor[Ds]#is
  ](val ami: Ami
  , val members: Ms
  , deps: Ds = ∅
  ) extends Bundle[Ds, Tw](deps) with AnyAWSDistribution {

  type AMI = Ami
  type Members = Ms 

}

```
