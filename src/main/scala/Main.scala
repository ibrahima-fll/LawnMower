import scopt.OParser
import utils.Config
import utils.Config.parser

class Main extends App {

  OParser.parse(parser, args, Config()) match {
    case Some(conf) => ???
    case _ => ??? //TODO: log error message.
  }
}