package org.talend.training.command

import java.util.UUID

import akka.actor.{Actor, Props}

object DatasetEntity {

  sealed trait Command {
    def id: UUID
  }
  case class CreateCommand(id: UUID, properties: String) extends Command

  def props() = Props(new DatasetEntity)

}

class DatasetEntity extends Actor {

  import DatasetEntity._

  override def receive: Receive = {
    case command: CreateCommand =>
      context.system.eventStream.publish(Events.DatasetCreated(command.id, command.properties))
  }

}
