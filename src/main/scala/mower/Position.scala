package mower

import lawn.Dimension

case class Position(x: Int, y: Int, headDirection: Direction) {
  /**
    * Updates the position according to headDirection to move forward.
    *
    * @return [[Position]]
    */
  private def forward: Position =
    headDirection match {
      case N => this.copy(y = y + 1)
      case S => this.copy(y = y - 1)
      case W => this.copy(x = x - 1)
      case E => this.copy(x = x + 1)
    }

  /**
    * Moves according to an input action and the dimension in which a movement is allowed.
    *
    * @param action    action to execute
    * @param dimension lawn dimension in which a movement is allowed.
    * @return [[Position]]
    */
  def move(action: Action, dimension: Dimension): Position =
    action match {
      case G => this.copy(headDirection = turnLeft)
      case D => this.copy(headDirection = turnRight)
      case A => if (isMoveAllowed(dimension))
        this.forward
      else this
    }

  /**
    * Moves the head of a [[Position]] to face the left of initial position.
    *
    * @return [[Direction]]
    */
  private[mower] def turnLeft: Direction = {
    headDirection match {
      case N => W
      case S => E
      case W => S
      case E => N
    }
  }

  /**
    * Moves the head of a [[Position]] to face the right of initial position.
    *
    * @return [[Direction]]
    */
  private[mower] def turnRight: Direction = {
    headDirection match {
      case N => E
      case S => W
      case W => N
      case E => S
    }
  }

  /**
    * Checks if moving forward is allowed.
    *
    * @param dimension the dimension in which a movement is allowed.
    * @return
    */
  private[mower] def isMoveAllowed(dimension: Dimension): Boolean = this match {
    case Position(0, _, W) | Position(_, 0, S) | Position(dimension.width, _, E) | Position(_, dimension.height, N) => false
    case _ => true
  }
}
