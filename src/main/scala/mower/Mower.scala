package mower

case class Mower(position: Position){
  def forward: Mower = this.copy(
    position = position.forward
  )

  def rotate(rotationDirection: RotationDirection): Mower = this.copy(
    position = position.rotate(rotationDirection)
  )
}