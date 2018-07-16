package org.talend.training.command

import java.util.UUID

object Events {

  sealed trait Event
  case class DatasetCreated(id: UUID, properties: String) extends Event

}
