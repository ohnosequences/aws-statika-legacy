package ohnosequences.tools.tests

import ohnosequences.tools._
import org.scalatest._

class GenerateDocs extends FunSuite {

  test("Run literator on the sources") {
    val results = Literator.literateDir(new java.io.File("src/main/scala/"), "docs/code")
    assert(results forall (_.successful))
  }
}
