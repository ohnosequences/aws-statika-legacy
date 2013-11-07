package ohnosequences.statika.aws.tests

import ohnosequences.statika._
import ohnosequences.statika.aws._
import org.scalatest._

class AMITests extends FunSuite {

  class SomeMetadata(val smth: String) extends AnyMetadata

  case object amimi extends AbstractAMI("id", "version") {
    type Metadata = SomeMetadata

    def userScript(
        md: Metadata
      , distName: String
      , bundleName: String
      , creds: AWSCredentials = RoleCredentials
      ): String =
        md.smth +" "+ distName +" "+ bundleName +"?"
  }

  case object bun extends Bundle()

  case class SomeSpecialMetadata(smth0: String) extends SomeMetadata(smth0)

  case object dist extends AWSDistribution(
      ami = amimi
    , members = bun :+: ∅
    , deps = ∅) {

    val metadata = SomeSpecialMetadata("¡whoa!")
  }

  test("Specific metadata in AMI") {
    assert(dist.ami.userScript(dist.metadata, dist.fullName, bun.fullName) === dist.userScript(bun))
    assert(dist.userScript(bun) === "¡whoa! "+dist.fullName+" "+bun.fullName+"?")
  }

}
