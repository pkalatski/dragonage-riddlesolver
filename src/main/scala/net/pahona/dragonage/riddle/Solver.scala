package net.pahona.dragonage.riddle

import scala.collection.Set

object Solver {
  type Riddle = Set[(Int, Int)]
  type Solution = Seq[(Int, Int)]

  def solve(riddle: Riddle): Solution = solve(riddle, startingPoints(riddle))

  private[this] def startingPoints(riddle: Riddle): Set[Int] = {
    val as = riddle.collect {
      case (a, _) => a
    }

    val bs = riddle.collect {
      case (_, b) => b
    }

    as ++ bs toSet
  }

  private[this] def solve(riddle: Riddle, startingPoints: Set[Int]): Solution = {
    require(!startingPoints.isEmpty)

    solve(riddle, Nil, startingPoints.head) match {
      case Some(solution) => solution
      case _ => solve(riddle, startingPoints.tail)
    }
  }

  private[this] def solve(riddle: Riddle, s: Solution, position: Int): Option[Solution] = {
    val possibleMoves = movesFrom(riddle, position) map ((position, _)) filter (missingFrom(s)) toList

    possibleMoves match {
      case Nil if solved(riddle, s) => Some(s)
      case Nil => None
      case x :: xs => solve(riddle, s :+ x, x._2)
    }
  }

  private[this] def missingFrom(s: Solution): ((Int, Int)) => Boolean = {
    case (a: Int, b: Int) => {
      !s.contains((a, b)) && !s.contains((b, a))
    }
  }

  private[this] def movesFrom(riddle: Riddle, position: Int): Set[Int] = {
    riddle filter linesWithPoint(position) map { case (a, b) =>
      if (a == position) b else a
    }
  }

  private[this] def linesWithPoint(point: Int): ((Int, Int)) => Boolean = {
    case (a, b) => a == point || b == point
  }

  private[this] def solved(riddle: Riddle, s: Solution): Boolean = {
    riddle.size == s.size
  }
}
