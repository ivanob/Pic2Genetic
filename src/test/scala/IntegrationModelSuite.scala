import genetic.common.Traits._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IntegrationModelSuite extends FunSuite{

  test("test definition of generation function") {
    val bodyGen = List(WrapInt(1))
    val bodyChromo = List(bodyGen)
    val bodyInd = List(bodyChromo)
    def gen1 = Gen(bodyGen, (t: List[WrapType[_]])=>{true})
    def chromo1 = Chromo(bodyChromo, (t: List[GenBody])=>{true})
    def indiv1 = Individual(bodyInd, (t: List[ChromoBody])=>{true})
    /*def generator1():Individual = {
      Individual(Chromo(), ), )
    }*/
  }

  test("test random generators") {
    //Generation of a Gen with 3 bits of info
    val gen3 = genIntRandomGenerator(3, ()=>1)
    assert(gen3 == List(WrapInt(1),WrapInt(1),WrapInt(1)))
    //Generation of a chromosome with 5 Gens
    val chromo5 = chromoRandomGenerator(5, ()=>{genIntRandomGenerator(3, ()=>1)})
    assert(chromo5 == List(List(WrapInt(1), WrapInt(1), WrapInt(1)),
      List(WrapInt(1), WrapInt(1), WrapInt(1)),
      List(WrapInt(1), WrapInt(1), WrapInt(1)),
      List(WrapInt(1), WrapInt(1), WrapInt(1)),
      List(WrapInt(1), WrapInt(1), WrapInt(1))))
    //Generation of an individual with 2 chromosomes
    val ind2 = individualRandomGenerator(2, ()=>{chromoRandomGenerator(5, ()=>{genIntRandomGenerator(3, ()=>1)})})
    assert(ind2 == List(
      List(List(WrapInt(1), WrapInt(1), WrapInt(1)),
        List(WrapInt(1), WrapInt(1), WrapInt(1)),
        List(WrapInt(1), WrapInt(1), WrapInt(1)),
        List(WrapInt(1), WrapInt(1), WrapInt(1)),
        List(WrapInt(1), WrapInt(1), WrapInt(1))),
      List(List(WrapInt(1), WrapInt(1), WrapInt(1)),
        List(WrapInt(1), WrapInt(1), WrapInt(1)),
        List(WrapInt(1), WrapInt(1), WrapInt(1)),
        List(WrapInt(1), WrapInt(1), WrapInt(1)),
        List(WrapInt(1), WrapInt(1), WrapInt(1)))))
  }

}
