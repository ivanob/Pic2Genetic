package genetic.common

import genetic.common.Types.Binary

object Traits {

  trait Codificable[T]{
    lazy val binaryRepr: Binary
    def encode():Binary
    def decode(bin: Binary):T
  }

  trait Gen extends Codificable[Gen]{
  }

  trait Chromosome extends Codificable[Chromosome]{
    val genes: List[Gen]
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
