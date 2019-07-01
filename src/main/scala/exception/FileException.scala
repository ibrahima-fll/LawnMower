package exception

abstract class FileException(msg: String, cause: Throwable = null) extends Exception(msg, cause)

case class NotFoundException(msg: String, cause: Throwable) extends FileException(msg, cause)

case class UnexpectedException(msg: String, cause: Throwable) extends FileException(msg, cause)

case class FileParsingException(msg: String, cause: Throwable) extends FileException(msg, cause)

object FileParsingException {
  def apply(msg: String, cause: Throwable = null): FileParsingException = new FileParsingException(msg, cause)
}