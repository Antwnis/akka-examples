import akka.actor._
//import akka.util.Duration
//import akka.util.duration._

class Worker extends Actor with ActorLogging {

  def receive = {
    case Work(start) =>
      // perform the work
      val result = start + 10
      log.info(s"I'm a new Worker. I was just requested to work starting from ${start} and did my work, with result ${result}" )
      sender ! Result(result)
  }


}
