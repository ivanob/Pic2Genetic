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

  case class Gen(v: List[WrapType[_]], life: (List[WrapType[_]])=>Boolean)

  case class Chromo(v: List[Gen], life: (List[Gen])=>Boolean)

  case class Individual(v: List[Chromo], life: (List[Chromo])=>Boolean)

  def genIntRandomGenerator(numGens: Int, randomInt: ()=>Int): GenBody = {
    List.tabulate(numGens)(_ => WrapInt(randomInt()))
  }

  case class IndividualRandomGenerator(generateRandomIndividual: ()=>Individual/*,
                          lifeIndividual:(List[Chromo])=>Boolean,
                          lifeChromo: (List[Gen])=>Boolean,
                          lifeGen: (List[WrapType[_]])=>Boolean*/
                         )
}
