package org.talend.training

import akka.actor.{Actor, ActorLogging, Timers}

import scala.concurrent.duration._

object ProcessorActor {

  case object TickKey
  case object TicToc
  case class MessageA(info: String)
  case class MessageB(info: String)

}

class ProcessorActor extends Actor with ActorLogging with Timers {

  import ProcessorActor._

  var i: Int = 0

  override def receive: Receive = {
    case m: MessageA =>
      i += 1
      log.info("receive MessageA {} {}", m.info, i)
    case m: MessageB =>
      i += 1
      log.info("receive MessageB {} {}", m.info, i)
      timers.startSingleTimer(TickKey, TicToc, 2.seconds)
    case TicToc =>
      // warning change sender
      sender() ! "nimp"
  }

}
