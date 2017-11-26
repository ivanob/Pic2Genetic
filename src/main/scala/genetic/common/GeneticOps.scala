package genetic.common

import genetic.common.Traits.Individual
import genetic.common.Types.Population


object GeneticOps {
  def initialization(size: Int): Population = ???
  def ordering(p: Population):Population = p.sorted
  def crossover(i1: Individual, i2: Individual, mergingPoint: Int): (Individual,Individual) = {
    val bin = (i1.encode(),i2.encode())
    val newBin1 = bin._1.take(mergingPoint) :: bin._2.drop(mergingPoint)
    val newBin2 = bin._2.take(mergingPoint) :: bin._1.drop(mergingPoint)
    (i1.decode(newBin1), i2.decode(newBin2))
  }
  def mutation(i: Individual, mutationPoint: Int): Individual = ???
  def selection(p: Population, numSelected: Int): Population = p.take(numSelected)
}
