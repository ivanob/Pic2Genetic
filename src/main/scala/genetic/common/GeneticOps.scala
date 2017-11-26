package genetic.common

import genetic.common.Traits.Individual
import genetic.common.Types.Population


object GeneticOps {
  def initialization(size: Int): Population = ???
  def ordering(p: Population):Population = ???
  def crossover(i1: Individual, i2: Individual, mergingPoint: Int) = ???
  def mutation(i: Individual, mutationPoint: Int): Individual = ???
  def selection(p: Population, numSelected: Int): Population = ???
}
