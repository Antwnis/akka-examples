import akka.actor.{Actor, ActorLogging}

class Accountant extends Actor with ActorLogging {

  override def receive: Receive = {
    case CompleteResult(result: Double, message: String) => {
      log.info(s"Horrey! Got the complete result: ${result} with message: ${message}" )
    }
  }

}
