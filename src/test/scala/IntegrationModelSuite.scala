import genetic.common.Traits._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IntegrationModelSuite extends FunSuite{

  test("test definition of generation function") {
    def gen1 = Gen(List(WrapInt(1)), (t: List[WrapType[_]])=>{true})
    def chromo1 = Chromo(List(gen1), (t: List[Gen])=>{true})
    def indiv1 = Individual(List(chromo1), (t: List[Chromo])=>{true})
    /*def generator1():Individual = {
      Individual(Chromo(), ), )
    }*/
  }

  test("test random generators") {
    val gen3 = genIntRandomGenerator(3, ()=>1)
    assert(gen3 == List(WrapInt(1),WrapInt(1),WrapInt(1)))
  }

}
