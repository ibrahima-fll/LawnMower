package file

import fastparse._
import lawn.Dimension
import mower._
import org.scalatest.{Matchers, WordSpec}


class ParserTest extends WordSpec with Matchers {

  "Parser" should {
    "parse" when {
      "given numbers" in {
        parse("42", Parser.number(_)) shouldBe Parsed.Success(42, 2)
      }

      "given space" in {
        parse("    ", Parser.space(_)) shouldBe Parsed.Success((), 4)
      }
      "given newline" in {
        parse(
          """
            |
          """.stripMargin, Parser.newLine(_)) shouldBe Parsed.Success((), 12)
      }
      "given action" in {
        parse("G", Parser.action(_)) shouldBe Parsed.Success(G, 1)
        parse("D", Parser.action(_)) shouldBe Parsed.Success(D, 1)
        parse("A", Parser.action(_)) shouldBe Parsed.Success(A, 1)
      }
      "given dimension" in {
        parse("5 5", Parser.dimension(_)) shouldBe Parsed.Success(Dimension(5, 5), 3)
      }
      "given direction" in {
        parse("N", Parser.direction(_)) shouldBe Parsed.Success(N, 1)
        parse("S", Parser.direction(_)) shouldBe Parsed.Success(S, 1)
        parse("W", Parser.direction(_)) shouldBe Parsed.Success(W, 1)
        parse("E", Parser.direction(_)) shouldBe Parsed.Success(E, 1)
      }
      "given position" in {
        parse("5 5 N", Parser.position(_)) shouldBe Parsed.Success(Position(5, 5, N), 5)
      }
      "given instructions" in {
        parse("GAGAGAGAA", Parser.instructions(_)) shouldBe Parsed.Success(Seq(G, A, G, A, G, A, G, A, A), 9)
      }
      "given configFile" in {
        parse(
          """5 5
            |1 2 N
            |GAGAGAGAA
            |3 3 E
            |AADAADADDA
          """.stripMargin, Parser.configFileParser(_)) shouldBe Parsed.Success(
          (Dimension(5, 5), Seq(
            (Position(1, 2, N), Seq(G, A, G, A, G, A, G, A, A)),
            (Position(3, 3, E), Seq(A, A, D, A, A, D, A, D, D, A))
          )), 47)
      }
    }
  }
}
