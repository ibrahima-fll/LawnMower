package lawn

import exception.{FileException, FileParsingException, _}
import fastparse.Parsed
import mower.{Action, Mower, Position}

case class Lawn(dimension: Dimension, mowers: Seq[Mower]) {
  def run: Seq[Mower] = mowers.map(_.executeActions(dimension))
}

object Lawn {
  /**
    * Creates a lawn with specific dimension and its mowers.
    *
    * @param fileContent Parsed content of a structured file
    * @return [[Either[FileException, Lawn]]
    */
  def apply(fileContent: Parsed[(Dimension, Seq[(Position, Seq[Action])])]): Either[FileException, Lawn] = {
    fileContent match {
      case Parsed.Failure(label, index, _) => Left(FileParsingException(parsingException(label, index)))
      case Parsed.Success(expression, _) =>
        val lawnDimension = expression._1
        val mowers = expression._2.map {
          case (position: Position, actions: Seq[Action]) => Mower(position, actions)
        }

        Right(Lawn(lawnDimension, mowers))
    }
  }
}