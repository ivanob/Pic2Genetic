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
  type ChromoBody = List[Gen]
  type IndividualBody = List[Chromo]

  /*** Functions to generate bodies ***/
  def genIntRandomGenerator(tamGen: Int, randomInt: ()=>Int, lifeFun: (GenBody)=>Boolean): Gen = {
    Gen(List.tabulate(tamGen)(_ => WrapInt(randomInt())), lifeFun)
  }
  def chromoRandomGenerator(numGens: Int, lifeFunChromo: (ChromoBody)=>Boolean)
                           (tamGen: Int, randomInt: ()=>Int, lifeFunGen: (GenBody)=>Boolean): Chromo = {
    val a = (1 to numGens).map(_=>genIntRandomGenerator(tamGen, randomInt, lifeFunGen)).toList
    Chromo(a, lifeFunChromo)
  }
  def individualRandomGenerator(numChromos: Int, lifeFunInd: (IndividualBody)=>Boolean)
                               (numGens: Int, lifeFunChromo: (ChromoBody)=>Boolean)
                               (tamGen: Int, randomInt: ()=>Int, lifeFunGen: (GenBody)=>Boolean): Individual = {
    val a = (1 to numChromos).map(_=>chromoRandomGenerator(numGens, lifeFunChromo)(tamGen, randomInt, lifeFunGen)).toList
    Individual(a, lifeFunInd)
  }

  case class Gen(v: GenBody, life: (GenBody)=>Boolean){require(life(v))}

  case class Chromo(v: ChromoBody, life: (ChromoBody)=>Boolean){require(life(v))}

  case class Individual(v: IndividualBody, life: (IndividualBody)=>Boolean){require(life(v))}

}
