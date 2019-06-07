package file

import fastparse._
import NoWhitespace._
import lawn.Dimension
import mower.{Action, Direction, Position}


object Parser {
  def number[_: P]: P[Int] = P(CharIn("0-9").rep(min = 1).!.map(_.toInt))

  def space[_: P]: P[Unit] = P(" ".rep)

  def action[_: P]: P[Action] = P(IgnoreCase("A") | IgnoreCase("G") | IgnoreCase("D")).!.map(Action.parse)

  def dimension[_: P]: P[Dimension] = P(number ~ space ~ number ~ End).map { case (x: Int, y: Int) => Dimension(x, y) }

  def direction[_: P]: P[Direction] = P("N" | "S" | "W" | "E").!.map(Direction.parse)

  def position[_: P]: P[Position] = P(number ~ space ~ number ~ direction ~ End).map {
    case (x: Int, y: Int, p: Position) => Position(x, y, p)
  }

  def instructions[_: P]: P[Seq[Action]] = P(action.rep ~ End)
}
