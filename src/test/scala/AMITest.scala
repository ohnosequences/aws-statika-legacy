package ohnosequences.statika.aws.tests

import ohnosequences.statika._
import ohnosequences.statika.aws._
import org.scalatest._

class AMITests extends FunSuite {

  case class SomeMetadata(val smth: String) extends AnyMetadata

  case object amimi extends AbstractAMI("id", "version") {
    type MetadataBound = SomeMetadata

    override def userScript[M <: MetadataBound]
      (md: M, distName: String, bundleName: String, creds: AWSCredentials = RoleCredentials): String =
        md.smth +" "+ distName +" "+ bundleName +"?"
  }

  case object bun extends Bundle() { def install[D <: AnyDistribution](d: D) = success(name) }

  case object dist extends AWSDistribution(
      metadata = SomeMetadata("¡whoa!")
    , ami = amimi
    , members = bun :+: ∅
    , deps = ∅) {

    def install[D <: AnyDistribution](d: D) = success(name) 
  }

  test("Specific metadata in AMI") {
    assert(dist.ami.userScript(dist.metadata, dist.fullName, bun.fullName) === dist.userScript(bun))
    assert(dist.userScript(bun) === "¡whoa! "+dist.fullName+" "+bun.fullName+"?")
  }

}
