package mower

case class Mower(position: Position){
  def move(action: Action): Mower = this.copy(
    position = position.move(action)
  )
}