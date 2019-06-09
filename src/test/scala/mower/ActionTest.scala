package mower

import org.scalatest.{Matchers, WordSpec}

class ActionTest extends WordSpec with Matchers{
  "Action" should {
    "be parsed correctly" in {
      Action.parse("a") shouldBe A
      Action.parse("A") shouldBe A
      Action.parse("G") shouldBe G
      Action.parse("D") shouldBe D
    }
  }
}
