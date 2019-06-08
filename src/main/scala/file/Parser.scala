package file

import fastparse._ , NoWhitespace._
import lawn.Dimension
import mower.{Action, Direction, Position}


object Parser {
  private[file] def number[_: P]: P[Int] = P(CharIn("0-9").rep(1).!.map(_.toInt))

  private[file] def space[_: P]: P[Unit] = P(" ".rep)

  private[file] def newLine[_: P]: P[Unit] = P((" " | "\t" | "\n").rep)

  private[file] def action[_: P]: P[Action] = P(IgnoreCase("A") | IgnoreCase("G") | IgnoreCase("D")).!.map(Action.parse)

  private[file] def dimension[_: P]: P[Dimension] = P(number ~ space ~ number).map { case (x: Int, y: Int) => Dimension(x, y) }

  private[file] def direction[_: P]: P[Direction] = P(IgnoreCase("N") | IgnoreCase("S") | IgnoreCase("W") | IgnoreCase("E")).!.map(Direction.parse)

  private[file] def position[_: P]: P[Position] = P(number ~ space ~ number ~ space ~ direction).map {
    case (x: Int, y: Int, p: Direction) => Position(x, y, p)
  }

  private[file] def instructions[_: P]: P[Seq[Action]] = P(action.rep)

  //todo: ADD Option for (mower, instruction) couple.
  private[file] def configFileParser[_: P]: P[(Dimension, Seq[(Position, Seq[Action])])] = P(
    dimension ~ newLine ~ (position ~ newLine ~ instructions ~ newLine.?).rep ~ End
  )


}
