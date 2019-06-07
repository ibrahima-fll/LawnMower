package process

import exception.FileException
import file.File

object Executor {

  def run(filename: String) = {
    val fileContent: Either[FileException, List[String]] = File.read(filename)

    fileContent.map { content =>
      val (lawnRawDimensions, rawInstructions) = content match {
        case h :: t => (h, t)
      }

      rawInstructions.map


    }
  }


  def getMowersPosition(l: List[String]): Either[Exception, List[(String, String)]] = {
    l match {
      case h::Nil => Left(new Exception("")) //todo: miaou
      case h::r::t => List((h, r)) ++ getMowersPosition(t)
      case h::t =>
    }
  }
}
