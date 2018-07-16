package org.talend.training.query

import java.util.UUID

import scala.concurrent.Future

object Repository {

  case class Dataset(id: UUID, properties: String)

}

trait Repository {

  import Repository._

  def save(dataset: Dataset): Future[Int]
  def load(id: UUID): Future[Option[Dataset]]
  def retrieveAllDatasets(): Future[Seq[Dataset]]

}
