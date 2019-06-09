package lawn

import mower._
import org.scalatest.{Matchers, WordSpec}

class LawnTest extends WordSpec with Matchers {
  "Lawn" should {
    "be created properly" in {

      val a = getClass.getResource("/conf.txt").getPath

      val lawn = Lawn(a)

      lawn.isRight shouldBe true

      lawn shouldBe Right(
        Lawn(
          Dimension(5, 5), Seq(
            Mower(Position(1, 2, N), Seq(G, A, G, A, G, A, G, A, A)),
            Mower(Position(3, 3, E), Seq(A, A, D, A, A, D, A, D, D, A))
          )
        )
      )

      lawn.right.get.mowers
        .map(_.executeActions(Dimension(5, 5))) shouldBe Seq(
        Mower(Position(1, 3, N), Seq()),
        Mower(Position(5, 1, E), Seq())
      )
    }
  }
}
