package org.talend.training.command

import akka.actor.Actor

class DatasetEntitySupervisor extends Actor {

  override def receive: Receive = {
    case command: DatasetEntity.Command =>
      val name = command.id.toString
      context child name getOrElse(context.actorOf(DatasetEntity.props(), name)) forward command
  }

}
