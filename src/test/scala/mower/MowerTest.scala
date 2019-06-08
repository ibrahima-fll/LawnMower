package mower

import org.scalatest.{Matchers, WordSpec}

class MowerTest extends WordSpec with Matchers {
  "Mower" should {
    "move according to instructions" in {
      val mower1 = Mower(Position(1, 2, N), Seq(G, A, G, A, G, A, G, A, A))
      val mower2 = Mower(Position(3, 3, E), Seq(A, A, D, A, A, D, A, D, D, A))

      val mowers = Seq(mower1, mower2)

      mowers.map(_.executeActions) shouldBe Seq(
        Mower(Position(1, 3, N), Seq()),
        Mower(Position(5, 1, E), Seq())
      )
    }
  }
}
