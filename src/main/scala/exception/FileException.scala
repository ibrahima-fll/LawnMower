package exception

trait FileException extends Throwable

case class NotFoundException(msg: String, cause: Throwable) extends FileException
case class UnexpectedException(msg: String, cause: Throwable) extends FileException
case class FileParsingException(msg: String, cause: Throwable) extends FileException

object FileParsingException {
  def apply(msg: String): FileParsingException = FileParsingException(msg, null)
}