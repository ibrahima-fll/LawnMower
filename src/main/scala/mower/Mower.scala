package mower

import lawn.Dimension

import scala.annotation.tailrec

case class Mower(position: Position, actions: Seq[Action]) {
  override lazy val toString = s"Mower(${position.x}, ${position.y}, ${position.headDirection})"

  def move(action: Action, dimension: Dimension): Mower =
    this.copy(position = position.move(action, dimension), actions = actions.drop(1))

  def executeActions(dimension: Dimension): Mower = {
    @tailrec
    def executeAction(mower: Mower): Mower = mower.actions match {
      case Nil => mower
      case h +: _ => executeAction(mower.move(h, dimension))
    }

    executeAction(this)
  }
}