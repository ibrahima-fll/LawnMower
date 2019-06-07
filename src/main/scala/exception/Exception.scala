package exception

trait FileException

case class NotFoundException(msg: String, cause: Throwable) extends FileException
case class UnexpectedException(msg: String, cause: Throwable) extends FileException