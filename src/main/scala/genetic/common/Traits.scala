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
  trait Individual extends Codificable{
    val chromosomes: List[Chromosome]\
  }
}
