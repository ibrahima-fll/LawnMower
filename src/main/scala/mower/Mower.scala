package mower

import lawn.Dimension

import scala.annotation.tailrec

case class Mower(position: Position, actions: Seq[Action]) {
  override lazy val toString = s"${position.x} ${position.y} ${position.headDirection}"

  /**
    * Moves a [[Mower]] according to an action and within the allowed movement range defined by a lawn's Dimension.
    *
    * @param action    action to execute
    * @param dimension dimension in which a movement is allowed.
    * @return
    */
  def move(action: Action, dimension: Dimension): Mower =
    this.copy(position = position.move(action, dimension), actions = actions.drop(1))

  /**
    * Executes a serie of [[Action]] within the allowed movement range defined by a lawn's Dimension.
    *
    * @param dimension dimension in which a movement is allowed.
    * @return
    */
  def executeActions(dimension: Dimension): Mower = {
    @tailrec
    def executeAction(mower: Mower): Mower = mower.actions match {
      case Nil => mower
      case h +: _ => executeAction(mower.move(h, dimension))
    }

    executeAction(this)
  }
}