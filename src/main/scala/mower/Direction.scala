package mower

sealed trait Direction

case object N extends Direction

case object S extends Direction

case object W extends Direction

case object E extends Direction

object Direction {
  def parse(s: String): Direction = s.toUpperCase match {
    case "N" => N
    case "S" => S
    case "W" => W
    case "E" => E
  }
}