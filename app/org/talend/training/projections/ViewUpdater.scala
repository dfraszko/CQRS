package org.talend.training.projections

import akka.actor.Actor
import javax.inject.Inject
import org.talend.training.command.Events
import org.talend.training.query.Repository
import org.talend.training.query.Repository.Dataset

class ViewUpdater @Inject()(repository: Repository) extends Actor {

  context.system.eventStream.subscribe(self, classOf[Events.Event])

  override def receive: Receive = {
    case event: Events.DatasetCreated =>
      repository.save(Dataset(event.id, event.properties))
  }

}
