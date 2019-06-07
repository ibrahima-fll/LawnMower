package object exception {
  val fileNotFoundError: String => String = (filename: String) => s"Unable to find file: $filename"
  val unexpectedError: String => String = (filename: String) => s"An unexpected error occured while reading file: $filename"
}
