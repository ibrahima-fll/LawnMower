package mower

import lawn.Dimension

case class Position(x: Int, y: Int, headDirection: Direction) {
  private def forward: Position =
    headDirection match {
      case N => this.copy(y = y + 1)
      case S => this.copy(y = y - 1)
      case W => this.copy(x = x - 1)
      case E => this.copy(x = x + 1)
    }

  def move(action: Action, dimension: Dimension): Position =
    action match {
      case G => this.copy(headDirection = turnLeft)
      case D => this.copy(headDirection = turnRight)
      case A => if(isMoveAllowed(dimension))
        this.forward
      else this
    }

  private[mower] def turnLeft: Direction = {
    headDirection match {
      case N => W
      case S => E
      case W => S
      case E => N
    }
  }

  private[mower] def turnRight: Direction = {
    headDirection match {
      case N => E
      case S => W
      case W => N
      case E => S
    }
  }

  private[mower] def isMoveAllowed(dimension: Dimension): Boolean = this match {
      case Position(0, _, W) | Position(_, 0, S) | Position(dimension.width, _, E) | Position(_, dimension.height, N) => false
      case _ => true
  }
}
