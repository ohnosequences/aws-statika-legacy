
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

  def userScript[B <: AnyBundle : isMember : isInstallable]
    (b: B, credentials: AWSCredentials = RoleCredentials): String =
      ami.userScript(this, b, credentials)
```

### Resources management

```scala
  val resourceBucket: Path = ""
```

Initial check, that we are running on the right AMI

```scala
  def setContext: InstallResults = ami.checkID
}
```

Just a constructor with AMI and sets of members/deps as type-parameters

```scala
abstract class AWSDistribution[
    A <: AbstractAMI
  , M <: TypeSet : ofBundles
  , D <: TypeSet : ofBundles
  , T <: HList   : towerFor[D]#is
  ](val ami: A
  , val members: M
  , deps: D = ∅
  ) extends Bundle[D, T](deps) with AnyAWSDistribution {

  type AMI = A
  type Members = M 

}

```
