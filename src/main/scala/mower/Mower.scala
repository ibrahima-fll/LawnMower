package mower

import scala.annotation.tailrec

case class Mower(position: Position, actions: Seq[Action]) {
  override lazy val toString = s"Mower(${position.x}, ${position.y}, ${position.headDirection})"

  def move(action: Action): Mower = this.copy(
    position = position.move(action),
    actions = actions.drop(1)
  )

  //TODO: do not move if next move will put the mower outside the lawn.
  def executeActions: Mower = {
    @tailrec
    def executeAction(mower: Mower): Mower = mower.actions match {
        case Nil => mower
        case h +: _ => executeAction(mower.move(h))
      }

    executeAction(this)
  }
}