package net.pahona.dragonage.riddle

object Main extends App {
  //  val riddle = Set((1, 2), (1, 3), (2, 3), (4, 2), (5, 3), (2, 6), (3, 6))
//  val riddle = Set((1,2), (1,4), (2,4), (3,4), (3,6), (6,4), (4,5), (4,7), (5,7), (4,8), (4,9), (4,12), (8,9),
//    (10,8), (10,12), (8,12), (9,12), (9,11), (12,11), (12,13), (12,14), (12,15), (12,16), (13,16), (14,15), (15,16),
//    (16,17), (15,18), (17,18))
  val riddle = Set((1,3), (1,4), (2,4), (2,5), (3,4), (4,5), (4,6), (4,7), (4,8), (4,9), (6,8), (6,10), (7,10), (7,9),
    (8,11), (8,10), (9,10), (9,12), (10,13), (11,13), (10,14), (12,14))


  val solution = Solver.solve(riddle)

  println("Press ENTER to see the next step")

  solution foreach { step =>
    println(step)
    scala.io.StdIn.readLine()
  }

  println("DONE")
}
