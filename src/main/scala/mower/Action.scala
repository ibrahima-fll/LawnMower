package mower

sealed trait Action

case object D extends Action
case object G extends Action
case object A extends Action

object Action {
  def parse(s: String): Action = s.toUpperCase match {
    case "A" => A
    case "D" => D
    case "G" => G
  }
}