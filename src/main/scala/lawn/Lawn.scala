package lawn

import exception.{FileException, FileParsingException, _}
import fastparse.Parsed
import file.File
import mower.{Action, Mower, Position}

case class Lawn(dimension: Dimension, mowers: Seq[Mower]) {
  def run: Seq[Mower] = mowers.map(_.executeActions)
}

object Lawn {
  def apply(filename: String): Either[FileException, Lawn] = {
    val fileContent: Either[FileException, Parsed[(Dimension, Seq[(Position, Seq[Action])])]] = File.read(filename)

    fileContent match {
      case Left(exception) => Left(exception)
      case Right(parsedExpression) => parsedExpression match {
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
}