import akka.actor.{ActorSystem, Props}

object Runner extends App {

  println("Running ActorSystem(Day1System)")
  calculate(nrOfWorkers = 10, nrOfElements = 10, nrOfMessages = 10)

  // actors and messages ...

  def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {

    // Create an Akka system
    val system = ActorSystem("Day1System")

    // create the result listener, which will print the result and shutdown the system
    val accountant = system.actorOf(Props[Accountant], name = "Accountant")

    // create the master
    val master = system.actorOf(Props(new Master(10,10, accountant)),name = "master")

    // start the calculation
    master ! LetTheWorkStart

  }

}



