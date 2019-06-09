package object exception {
  val fileNotFoundError: String => String = (filename: String) => s"Unable to find file: $filename"
  val unexpectedError: String => String = (filename: String) => s"An unexpected error occurred while reading file: $filename"
  val parsingException: (String, Int) => String = (label: String, index: Int) => s"Unable to parse $label at index $index"
}
