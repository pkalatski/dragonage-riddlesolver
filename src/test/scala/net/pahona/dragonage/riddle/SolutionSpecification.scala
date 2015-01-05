package net.pahona.dragonage.riddle

import net.pahona.dragonage.riddle.Solver.{Riddle, Solution}
import org.scalacheck._
import org.scalacheck.Prop._

object SolutionSpecification extends Properties("Solution") {
  val riddle1 = Set((1,2), (1,3), (2,3), (4,2), (5,3), (2,6), (3,6))
  val riddle2 = Set((1,2), (1,4), (2,4), (3,4), (3,6), (6,4), (4,5), (4,7), (5,7), (4,8), (4,9), (4,12), (8,9),
    (10,8), (10,12), (8,12), (9,12), (9,11), (12,11), (12,13), (12,14), (12,15), (12,16), (13,16), (14,15), (15,16),
    (16,17), (15,18), (17,18))

  def solvedRiddles: Gen[(Riddle, Solution)] = Gen.oneOf(riddleWithSolution(riddle1), riddleWithSolution(riddle2))

  def riddleWithSolution(r: Riddle) = (r, Solver.solve(r))

  property("all lines drawn") = forAll(solvedRiddles) {
    case (r: Riddle, s: Solution) => {
      r.forall { case (a: Int, b: Int) =>
        s.contains((a, b)) || s.contains(b, a)
      }
    }
  }

  property("no polyline terminations") = forAll(solvedRiddles) {
    case (_, s: Solution) => unterminated(s)
  }

  property("no repeated lines") = forAll(solvedRiddles) {
    case (_, s: Solution) => {
      s forall { case (a: Int, b: Int) => {
        s.count { line =>
          line == (a, b) || line == (b, a)
        } == 1
      }
      }
    }
  }

  private[this] def unterminated(s: Solution): Boolean = s match {
    case a :: b :: rest => {
      a._2 == b._1 && unterminated(b :: rest)
    }
    case _ => true
  }
}
