package genetic.common


object Types {
  type Binary = List[Int]
  type Population = List[Individual]
  type Gen = AnyVal
  type Chromosome = List[Gen]
  type Individual = List[Chromosome]
}
