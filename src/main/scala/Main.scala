import lawn.Lawn
import scopt.OParser
import utils.Config
import utils.Config.parser

object Main extends App {
  OParser.parse(parser, args, Config()) match {
    case Some(conf) => Lawn(conf.filename) match {
      case Right(lawn) => lawn.run.foreach(println)
      case Left(exception) => throw exception
    }

    case _ => ()
  }
}