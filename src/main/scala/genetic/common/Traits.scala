package genetic.common

import genetic.common.Types.Binary

object Traits {

  sealed trait Codificable[T]{
    def encode():Binary
    def decode(bin: Binary):T
    def binaryLength():Int
  }

  sealed trait WrapType[A] extends Codificable[A]
  case class WrapInt(value:Int) extends WrapType[Int] {
    override def encode(): Binary = ???

    override def decode(bin: Binary): Int = ???

    override def binaryLength(): Int = ???
  }
  case class WrapFloat(value:Float) extends WrapType[Float] {
    override def encode(): Binary = ???

    override def decode(bin: Binary): Float = ???

    override def binaryLength(): Int = ???
  }

  type GenBody = List[WrapType[_]]
  type ChromoBody = List[GenBody]
  type IndividualBody = List[ChromoBody]

  case class Gen(v: GenBody, life: (GenBody)=>Boolean)

  case class Chromo(v: ChromoBody, life: (ChromoBody)=>Boolean)

  case class Individual(v: IndividualBody, life: (IndividualBody)=>Boolean)

  def genIntRandomGenerator(numGens: Int, randomInt: ()=>Int): GenBody = {
    List.tabulate(numGens)(_ => WrapInt(randomInt()))
  }
  def chromoRandomGenerator(numGens: Int, randomGenBody: ()=>GenBody): ChromoBody = {
    List.tabulate(numGens)(_ => randomGenBody())
  }
  def individualRandomGenerator(numChromos: Int, randomChromoBody: ()=>ChromoBody): IndividualBody = {
    List.tabulate(numChromos)(_ => randomChromoBody())
  }

  case class IndividualRandomGenerator(generateRandomIndividual: ()=>Individual/*,
                          lifeIndividual:(List[Chromo])=>Boolean,
                          lifeChromo: (List[Gen])=>Boolean,
                          lifeGen: (List[WrapType[_]])=>Boolean*/
                         )
}
