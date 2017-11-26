package genetic.common

import genetic.common.Types.Binary

object Traits {

  trait Codificable{
    def encode():Binary
    def decode():Binary
  }

  trait Gen extends Codificable{

  }

  trait Chromosome extends Codificable{
    val genes: List[Gen]
  }

  trait Individual extends Codificable with Ordered[Individual]{
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
