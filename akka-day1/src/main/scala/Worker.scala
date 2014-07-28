import akka.actor._
import akka.routing.RoundRobinRouter
//import akka.util.Duration
//import akka.util.duration._

class Worker extends Actor {

  def receive = {
    case Work(start) =>
      println(s"I'm a new Worker. I was just requested to work starting from ${start}" )
      // perform the work
      val result = start + 10
      sender ! Result(result)
  }


}
