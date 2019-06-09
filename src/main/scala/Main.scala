import exception.FileParsingException
import fastparse.Parsed
import file.File
import lawn.Lawn
import scopt.OParser
import utils.Config
import utils.Config.parser
import exception.parsingException

object Main extends App {
  OParser.parse(parser, args, Config()) match {
    case Some(conf) =>
      File.read(conf.filename) match {
        case Left(exception) => throw exception
        case Right(parsedContent) => parsedContent match {
          case Parsed.Failure(label, index, _) => throw FileParsingException(parsingException(label, index))
          case Parsed.Success(fileContent, _) => Lawn(fileContent).run.foreach(println)
        }
      }
    case _ => ()
  }
}