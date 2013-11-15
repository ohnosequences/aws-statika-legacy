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

## Abstract AWS distributions

```scala
package ohnosequences.statika.aws

import shapeless._
import ohnosequences.statika._
import ohnosequences.typesets._

trait AnyAWSDistribution extends AnyDistribution {
```

### Metadata

Metadata contains deployment specific information. See [Metadata source](Metadata.md).

```scala
  type Metadata <: AnyMetadata
  val  metadata: Metadata
```

### AMI dependency

We have a generic `AbstractAMI` type and every AWS distribution contains an 
instance of it as all the members/bundles of the distribution are supposed 
to be installed in the same environment.

```scala
  type AMI <: AnyAMI.of[Metadata]
  val ami: AMI

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
    MD  <: AnyMetadata
  , Ami <: AnyAMI.of[MD]
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

```

