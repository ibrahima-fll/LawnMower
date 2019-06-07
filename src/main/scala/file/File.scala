package file

import java.io.FileNotFoundException

import exception.NotFoundException
import exception._

import scala.io.Source
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object File {
  def read(filename: String): Either[FileException, List[String]] =
    Try {
      val source = Source.fromFile(filename)
      val rows = source.getLines().toList
      source.close

      rows
    } match {
      case Success(value) => Right(value)
      case Failure(exception: FileNotFoundException) => Left(
        NotFoundException(fileNotFoundError(filename), exception)
      )
      case Failure(NonFatal(exception)) => Left(
        UnexpectedException(unexpectedError(filename), exception)
      )
    }
}