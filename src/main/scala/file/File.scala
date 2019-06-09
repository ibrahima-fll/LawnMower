package file

import java.io.FileNotFoundException

import exception.{NotFoundException, _}
import fastparse._
import lawn.Dimension
import mower.{Action, Position}

import scala.io.Source
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object File {
  /**
    * Reads a file content and return either the content or the encountered exception while trying to parse it.
    *
    * @param filename filepath to read
    * @return [[Either[FileException, Parsed[(Dimension, Seq[(Position, Seq[Action])])]]
    */
  def read(filename: String): Either[FileException, Parsed[(Dimension, Seq[(Position, Seq[Action])])]] =
    Try {
      val source = Source.fromFile(filename)
      val file = source.mkString
      source.close

      parse(file, Parser.configFileParser(_))
    } match {
      case Success(value) => Right(value)
      case Failure(exception: FileNotFoundException) => Left(
        NotFoundException(fileNotFoundError(filename), exception)
      )
      case Failure(NonFatal(exception: Exception)) => Left(
        UnexpectedException(unexpectedError(filename), exception)
      )
    }

}