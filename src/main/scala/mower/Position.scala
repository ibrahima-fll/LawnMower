package mower

case class Position(x: Int, y: Int, headDirection: Direction) {
  def forward: Position =
    headDirection match {
      case N => this.copy(y = y + 1)
      case S => this.copy(y = y - 1)
      case W => this.copy(x = x - 1)
      case E => this.copy(x = x + 1)
    }

  def rotate(rotationDirection: RotationDirection): Position =
    rotationDirection match {
      case G => this.copy(headDirection = turnLeft)
      case D => this.copy(headDirection = turnRight)
    }

  private def turnLeft: Direction = {
    headDirection match {
      case N => W
      case S => E
      case W => S
      case E => N
    }
  }

  private def turnRight: Direction = {
    headDirection match {
      case N => E
      case S => W
      case W => N
      case E => S
    }
  }
}
