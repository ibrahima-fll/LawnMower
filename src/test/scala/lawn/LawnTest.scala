package lawn

import mower._
import org.scalatest.{Matchers, WordSpec}

class LawnTest extends WordSpec with Matchers {
  "Lawn" should {
    "be created properly" in {

      val fileContent: (Dimension, Seq[(Position, Seq[Action])]) =
        (Dimension(5, 5), Seq(
          (Position(1, 2, N), Seq(G, A, G, A, G, A, G, A, A)),
          (Position(3, 3, E), Seq(A, A, D, A, A, D, A, D, D, A))
        ))

      val lawn = Lawn(fileContent)

      lawn shouldBe Lawn(
          Dimension(5, 5), Seq(
            Mower(Position(1, 2, N), Seq(G, A, G, A, G, A, G, A, A)),
            Mower(Position(3, 3, E), Seq(A, A, D, A, A, D, A, D, D, A))
          )
        )

      lawn.mowers
        .map(_.executeActions(Dimension(5, 5))) shouldBe Seq(
        Mower(Position(1, 3, N), Seq()),
        Mower(Position(5, 1, E), Seq())
      )
    }
  }
}
