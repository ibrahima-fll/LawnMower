package lawn

import fastparse.Parsed
import mower._
import org.scalatest.{Matchers, WordSpec}

class LawnTest extends WordSpec with Matchers {
  "Lawn" should {
    "be created properly" in {

      val fileContent: Parsed[(Dimension, Seq[(Position, Seq[Action])])] =
        Parsed.Success((Dimension(5, 5), Seq(
          (Position(1, 2, N), Seq(G, A, G, A, G, A, G, A, A)),
          (Position(3, 3, E), Seq(A, A, D, A, A, D, A, D, D, A))
        )), 44)

      val lawn = Lawn(fileContent)

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
