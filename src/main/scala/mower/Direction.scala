package mower

sealed trait Direction

case object N extends Direction
case object S extends Direction
case object W extends Direction
case object E extends Direction