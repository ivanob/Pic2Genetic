import genetic.common.Traits._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IntegrationModelSuite extends FunSuite{

  test("test definition of generation functions"){
    //Generation of Gens
    val gen1 = genIntRandomGenerator(5, ()=>1, (_)=>true)
    assert(gen1.v == List(WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1)))
    val gen2 = genIntRandomGenerator(1, ()=>99, (_)=>true)
    assert(gen2.v == List(WrapInt(99)))
    try {
      val gen3 = genIntRandomGenerator(1, () => 99, (_) => false)
      fail("It is not possible to build a Gen if the life function returns false")
    }catch {
      case _: IllegalArgumentException =>
    }
    //Generation of Chromosomes
    val chromo1 = chromoRandomGenerator(2, (_)=>true)(5, ()=>1, (_)=>true)
    assert(chromo1.v == List(List(WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1))),
      List(WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1)))
    try {
      val chromo2 = chromoRandomGenerator(1, (_)=>false)(5, ()=>1, (_)=>true)
      fail("It is not possible to build a Chromo if the life function returns false")
    }catch {
      case _: IllegalArgumentException =>
    }
    //Generation of Individual
    val ind1 = individualRandomGenerator(1, (_)=>true)(2, (_)=>true)(5, ()=>1, (_)=>true)
    assert(ind1.v == List(
      List(List(WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1))),
      List(WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1))
      ,
      List(List(WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1))),
      List(WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1),WrapInt(1))
    ))
    try {
      val ind2 = individualRandomGenerator(1, (_)=>true)(2, (_)=>true)(5, ()=>1, (_)=>true)
      fail("It is not possible to build a Individual if the life function returns false")
    }catch {
      case _: IllegalArgumentException =>
    }
  }

}
