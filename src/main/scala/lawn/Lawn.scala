package lawn

import mower.{Action, Mower, Position}

case class Lawn(dimension: Dimension, mowers: Seq[Mower]) {
  def run: Seq[Mower] = mowers.map(_.executeActions(dimension))
}

object Lawn {
  /**
    * Creates a lawn with specific dimension and its mowers.
    *
    * @param fileContent Parsed content of a structured file
    * @return [[Lawn]]
    */
  def apply(fileContent: (Dimension, Seq[(Position, Seq[Action])])): Lawn = {
    val lawnDimension = fileContent._1
    val mowers = fileContent._2.map {
      case (position: Position, actions: Seq[Action]) => Mower(position, actions)
    }

    Lawn(lawnDimension, mowers)
  }
}