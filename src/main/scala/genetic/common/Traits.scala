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

  /*sealed trait WrapType[A] extends Codificable[A]
  case class WrapInt(value:Int) extends WrapType[Int] {
    override def encode(): Binary = ???

    override def decode(bin: Binary): Int = ???

    override def binaryLength(): Int = ???
  }
  case class WrapFloat(value:Float) extends WrapType[Float] {
    override def encode(): Binary = ???

    override def decode(bin: Binary): Float = ???

    override def binaryLength(): Int = ???
  }*/

  type GenBody = List[WrapType[_]]
  type ChromoBody = List[GenBody]
  type IndividualBody = List[ChromoBody]

  /*** Functions to generate bodies ***/
  def genIntBodyRandomGenerator(numGens: Int, randomInt: ()=>Int): GenBody = {
    List.tabulate(numGens)(_ => WrapInt(randomInt()))
  }
  /*def genRandomGenerator[A](numGens: Int, random: ()=>A): GenBody = {
    List.tabulate(numGens)(_ => WrapInt(random()))
  }*/
  def chromoBodyRandomGenerator(numGens: Int, randomGenBody: ()=>GenBody): ChromoBody = {
    List.tabulate(numGens)(_ => randomGenBody())
  }
  def individualBodyRandomGenerator(numChromos: Int, randomChromoBody: ()=>ChromoBody): IndividualBody = {
    List.tabulate(numChromos)(_ => randomChromoBody())
  }

  case class Gen(v: GenBody, life: (GenBody)=>Boolean)

  case class Chromo(v: ChromoBody, life: (ChromoBody)=>Boolean)

  case class Individual(v: IndividualBody, life: (IndividualBody)=>Boolean)

  case class IndividualRandomGenerator(generateRandomIndividual: ()=>Individual/*,
                          lifeIndividual:(List[Chromo])=>Boolean,
                          lifeChromo: (List[Gen])=>Boolean,
                          lifeGen: (List[WrapType[_]])=>Boolean*/
                         )
}
