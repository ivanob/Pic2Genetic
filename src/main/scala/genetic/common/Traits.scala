package genetic.common

import genetic.common.Types.Binary

object Traits {

  trait Codificable[T]{
    def encode():Binary
    def decode(bin: Binary):T
    def binaryLength():Int
  }

  trait Gen[A <: AnyVal] extends Codificable[Gen]{
    val a: A
  }

  abstract case class Chromosome(val genes: List[Gen]) extends Codificable[Chromosome]{

    def encode():Binary = {
      genes.foldRight(List[Binary]())((x, acc) => List(x.encode()):::acc)
    }

    def decode(bin: Binary):Chromosome = {
      def go(genes: List[Gen], bin: Binary): List[Gen] = genes match {
        case x :: xs =>
        case Nil => Nil
      }
      //val g = genes.foldRight(x => x.decode(bin.slice(0, x.binaryLength())))
      val g = go(bin, genes)
      Chromosome(g)
    }

    def binaryLength():Int = {
      genes.foldRight(0)((x, acc) => acc+x.binaryLength())
    }

  }

  trait Individual extends Codificable[Individual] with Ordered[Individual]{
    val chromosomes: List[Chromosome]
    lazy val fitness = evalFitness()

    def evalFitness():Float
    def compare(that: Individual) = {
      if (this.fitness == that.fitness) 0
      else if (this.fitness > that.fitness) 1
      else -1
    }
  }
}
