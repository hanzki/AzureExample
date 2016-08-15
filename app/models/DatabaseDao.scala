package models

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * Created by hanzki on 13/07/16.
  */
class DatabaseDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  class Entities(tag: Tag) extends Table[Entity](tag, "ENTITIES") {
    def id = column[Option[Int]]("ID", O.PrimaryKey, O.AutoInc)
    def value = column[String]("VALUE")

    def * = (id, value) <> ((t: (Option[Int], String)) => Entity(t._1, t._2), Entity.unapply)
  }

  val entities = TableQuery[Entities]

  def insert(e: Entity): Future[Entity] = db.run(entities.insertOrUpdate(e)).map(_ => e)

  def get(id: Int): Future[Option[Entity]] = db.run(entities.filter(_.id === id).result.headOption)

  def all(): Future[Seq[Entity]] = db.run(entities.result)

}

