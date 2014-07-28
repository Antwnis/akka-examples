sealed trait day1Message
// Master -> Master : Let's start the whole thing
case class LetTheWorkStart() extends day1Message
// Master -> Worker : Let a worker know that there is work to be done
case class Work(value: Double) extends day1Message
// Worker -> Master : Let the master know that i've done my work
case class Result(value: Double) extends day1Message
// Master -> Printer : Let the printed know the final result - and how long it took to compute it
case class CompleteResult(i: Int) extends day1Message
