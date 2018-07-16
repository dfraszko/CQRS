package org.talend.training.query

import java.util.UUID

import javax.inject.{Inject, Singleton}
import org.talend.training.query.Repository.Dataset
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.inject.ApplicationLifecycle
import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

class Datasets(tag: Tag) extends Table[Dataset](tag, "DATASETS") {

  def id         = column[UUID]("ID")
  def properties = column[String]("properties")

  override def * = (id, properties) <> (Dataset.tupled, Dataset.unapply)

}

// https://www.playframework.com/documentation/2.6.x/PlaySlick#Usage
@Singleton
class SlickH2Repository @Inject()(lifecycle: ApplicationLifecycle,
                                  override val dbConfigProvider: DatabaseConfigProvider)
    extends Repository
    with HasDatabaseConfigProvider[H2Profile] {

  val datasets = TableQuery[Datasets]

  // Create the H2 database schema
  db.run(datasets.schema.create)
  lifecycle.addStopHook(() => db.run(datasets.schema.drop))

  override def save(dataset: Dataset): Future[Int] =
    db.run(datasets += dataset)

  override def load(id: UUID): Future[Option[Dataset]] =
    db.run(datasets.filter(_.id === id).result.headOption)

  override def retrieveAllDatasets(): Future[Seq[Repository.Dataset]] =
    db.run(datasets.result)

}
