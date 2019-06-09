package utils

import scopt.OParser

case class Config(filename: String = "")

object Config {
  private val builder = OParser.builder[Config]

  /**
    * Input command line parameters parser.
    */
  val parser: OParser[Unit, Config] = {
    import builder._
    OParser.sequence(
      programName("LawnMower"),
      opt[String]('f', "filename")
        .required()
        .valueName("<file>")
        .action((x, c) => c.copy(filename = x))
        .text("filename is required."),
    )
  }
}