package org.talend.training.controllers

import java.util.UUID

import akka.actor.ActorRef
import akka.util.Timeout
import javax.inject.{Inject, Named}
import org.talend.training.command.DatasetEntity
import org.talend.training.query.Repository
import org.talend.training.query.Repository.Dataset
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class ApplicationController @Inject()(@Named("dataset-supervisor") supervisor: ActorRef,
                                      repository: Repository,
                                      cc: ControllerComponents)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  implicit val timeout: Timeout = 2.seconds

  implicit val datasetWrites = Json.writes[Dataset]

  def index = Action {
    Ok
  }

  def createDataset() = Action {
    supervisor ! DatasetEntity.CreateCommand(UUID.randomUUID(), "")
    Accepted
  }

  def retrieveDatasets() = Action.async {
    repository.retrieveAllDatasets().map(datasets => Ok(Json.toJson(datasets)))
  }

  def retrieveDataset(id: String) = TODO

  def updateDataset(id: String) = TODO

  def deleteDataset(id: String) = TODO

}
