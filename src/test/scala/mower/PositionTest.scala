package mower

import lawn.Dimension
import org.scalatest.{Matchers, WordSpec}

class PositionTest extends WordSpec with Matchers {
  "Position" should {
    "be correct" when {
      val positionN = Position(0, 0, N)
      val positionW = Position(0, 0, W)
      val positionE = Position(0, 0, E)
      val positionS = Position(0, 0, S)
      "turning left" in {
        positionN.turnLeft shouldBe W
        positionW.turnLeft shouldBe S
        positionE.turnLeft shouldBe N
        positionS.turnLeft shouldBe E
      }
      "turning right" in {
        positionN.turnRight shouldBe E
        positionW.turnRight shouldBe N
        positionE.turnRight shouldBe S
        positionS.turnRight shouldBe W
      }
      "moving forward" in {
        positionN.move(A, Dimension(5, 5)) shouldBe Position(0, 1, N)
        positionE.move(A, Dimension(5, 5)) shouldBe Position(1, 0, E)
      }
      "when trying to go outside the lawn" in {
        positionW.move(A, Dimension(5, 5)) shouldBe Position(0, 0, W)
        positionS.move(A, Dimension(5, 5)) shouldBe Position(0, 0, S)
      }
    }
  }

}
