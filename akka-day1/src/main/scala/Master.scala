import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.routing.RoundRobinPool

class Master(numberOfWorkers:Int = 10, expectedMessages:Int = 10, accountant: ActorRef) extends Actor with ActorLogging {

  val startValue = 0
  var finalResult = 0D
  var numberOfResults = 0

  // Round-robin 10 workers
  val workerRouter = context.actorOf(
    Props[Worker].withRouter(RoundRobinPool(numberOfWorkers)), name = "workerRouter")

  // handle messages ...
  def receive = {
    case LetTheWorkStart => {
      log.info("Master -> Got LetTheWorkStart")
      log.info("Master -> Let's create ${numberOfWorkers} workers")
      for (i <- 0 to numberOfWorkers) {
        workerRouter ! Work(startValue)
      }
    }
    case Result(result: Double) => {
      finalResult += result
      numberOfResults += 1

      log.info(s"Received result from 1 worker : ${result}")

      if (numberOfResults == expectedMessages) {
        // Send the result to the Accountant
        accountant ! CompleteResult(finalResult, "DONE")

        // listener ! PiApproximation(pi, duration = (System.currentTimeMillis - start).millis)
        // Stops this actor and all its supervised children
        context.stop(self)
      }
    }
    case _ => {
      log.info("received something " )
    }
  }

}
