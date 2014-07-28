import akka.actor.{Props, ActorSystem}

class Runner extends App {

  calculate(nrOfWorkers = 10, nrOfElements = 10, nrOfMessages = 10)

  // actors and messages ...

  def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {

    // Create an Akka system
    val system = ActorSystem("Day1System")

    // create the result listener, which will print the result and shutdown the system
//    val listener = system.actorOf(Props[Listener], name = "listener")

    // create the master
    val master = system.actorOf(Props(new Master()), //, listener)),
      name = "master")

    // start the calculation
    master ! LetTheWorkStart

  }

}



