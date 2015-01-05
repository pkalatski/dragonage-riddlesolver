package net.pahona.dragonage.riddle

import java.nio.file.{Paths, Files}

import net.pahona.dragonage.riddle.Solver.Riddle

import scala.collection.JavaConversions._

object Main extends App {
  val riddle = readRiddle()

  val solution = Solver.solve(riddle)

  println("Press ENTER to see the next step")

  solution foreach { step =>
    println(s"${step._1}->${step._2}")
    scala.io.StdIn.readLine()
  }

  println("DONE")

  private[this] def readRiddle(): Riddle = {
    Files.readAllLines(Paths.get("riddle.txt")) map parseLine toSet
  }

  private[this] def parseLine(lineWithDash: String) = {
    val arr = lineWithDash.split('-')
    (arr(0).toInt, arr(1).toInt)
  }
}
