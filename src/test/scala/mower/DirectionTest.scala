package mower

import org.scalatest.{Matchers, WordSpec}

class DirectionTest extends WordSpec with Matchers {
  "Direction" should {
    "be parsed correctly" in {
      Direction.parse("n") shouldBe N
      Direction.parse("N") shouldBe N
      Direction.parse("W") shouldBe W
      Direction.parse("S") shouldBe S
      Direction.parse("E") shouldBe E
    }
  }

}
