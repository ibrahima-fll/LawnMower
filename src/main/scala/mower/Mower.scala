package mower

import lawn.Dimension

import scala.annotation.tailrec

case class Mower(position: Position, actions: Seq[Action]) {
  override lazy val toString = s"Mower(${position.x}, ${position.y}, ${position.headDirection})"

  def move(action: Action, dimension: Dimension): Mower = if (this.isMoveAllowed(action, dimension))
    this.copy(position = position.move(action), actions = actions.drop(1))
  else this

  //TODO: do not move if next move will put the mower outside the lawn.
  def executeActions(dimension: Dimension): Mower = {
    @tailrec
    def executeAction(mower: Mower): Mower = mower.actions match {
      case Nil => mower
      case h +: _ => executeAction(mower.move(h, dimension))
    }

    executeAction(this)
  }

  def isMoveAllowed(action: Action, dimension: Dimension): Boolean = action match {
    case A => position match {
      case Position(0, _, W) | Position(_, 0, S) | Position(dimension.width, _, E) | Position(_, dimension.height, N) => false
      case _ => true
    }
    case _ => true
  }
}