import akka.actor.{Props, Actor}
import akka.routing.{RoundRobinPool}

class Master(numberOfWorkers:Int = 10, expectedMessages:Int = 10) extends Actor {

  val startValue = 0
  var finalResult = 0
  var numberOfResults = 0

  // Round-robin 10 workers
  val workerRouter = context.actorOf(
    Props[Worker].withRouter(RoundRobinPool(numberOfWorkers)), name = "workerRouter")

  // handle messages ...
  def receive = {
    case LetTheWorkStart() => {
      for (i <- 0 to numberOfWorkers)
        workerRouter ! Work(startValue)
    }
    case CompleteResult(result: Int) => {
      finalResult += result
      numberOfResults += 1

      if (numberOfResults == expectedMessages) {
        // Send the result to the listener
        // listener ! PiApproximation(pi, duration = (System.currentTimeMillis - start).millis)
        // Stops this actor and all its supervised children
        context.stop(self)
      }
    }
  }

}
