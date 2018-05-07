package genetic.common

import genetic.common.Types.Binary

object Traits {

  sealed trait Codificable[T]{
    def encode(i:T):Binary
    def decode(bin: Binary):T
    def binaryLength():Int
  }

  trait IntCodificable extends Codificable[Int]{
    override def encode(i: Int): Binary = ???

    override def decode(bin: Binary): Int = ???

    override def binaryLength(): Int = ???
  }
  trait FloatCodificable extends Codificable[Float]{
    override def encode(i: Float): Binary = ???

    override def decode(bin: Binary): Float = ???

    override def binaryLength(): Int = ???
  }

  sealed trait WrapType[A]
  case class WrapInt(value:Int) extends WrapType[Int] with IntCodificable
  case class WrapFloat(value:Float) extends WrapType[Float] with FloatCodificable

  type GenBody = List[WrapType[_]]
  type ChromoBody = List[GenBody]
  type IndividualBody = List[ChromoBody]

  /*** Functions to generate bodies ***/
  def genIntBodyRandomGenerator(numGens: Int, randomInt: ()=>Int): GenBody = {
    List.tabulate(numGens)(_ => WrapInt(randomInt()))
  }
  def chromoBodyRandomGenerator(numGens: Int, randomGenBody: ()=>GenBody): ChromoBody = {
    List.tabulate(numGens)(_ => randomGenBody())
  }
  def individualBodyRandomGenerator(numChromos: Int, randomChromoBody: ()=>ChromoBody): IndividualBody = {
    List.tabulate(numChromos)(_ => randomChromoBody())
  }
  //Generator of a full individual
  def individualGenerator(numChromos:Int, numGens:Int): Individual ={
    individualBodyRandomGenerator(2, ()=>{chromoBodyRandomGenerator(5, ()=>{genIntBodyRandomGenerator(3, ()=>1)})})
  }

  case class Gen(v: GenBody, life: (GenBody)=>Boolean)

  case class Chromo(v: List[Gen], life: (ChromoBody)=>Boolean)

  case class Individual(v: List[Chromo], life: (IndividualBody)=>Boolean)

}
