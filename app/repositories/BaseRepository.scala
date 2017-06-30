package repositories

import scala.concurrent.Future

import javax.inject._

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }

import slick.jdbc.PostgresProfile.api._
import slick.jdbc.{ JdbcProfile, PostgresProfile }
import slick.lifted.{ CanBeQueryCondition, AbstractTable }

import models.{ BaseModel, BaseTable }

trait BaseRepositoryQuery[T <: BaseTable[E], E <: BaseModel] {

  val query: PostgresProfile.api.type#TableQuery[T]

  def getByIdQuery(id: Long) = {
    query.filter(_.id === id)
  }

  def getAllQuery = {
    query
  }

  def filterQuery[C <: Rep[_]](expr: T => C)(implicit wt: CanBeQueryCondition[C]) = {
    query.filter(expr)
  }

  def saveQuery(row: E) = {
    query returning query += row
  }

  def deleteByIdQuery(id: Long) = {
    query.filter(_.id === id).delete
  }

  def updateByIdQuery(id: Long, row: E) = {
    query.filter(_.id === id).update(row)
  }
}


abstract class BaseRepository[T <: BaseTable[E], E <: BaseModel] @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends BaseRepositoryQuery[T, E]
  with HasDatabaseConfigProvider[JdbcProfile] {


  def getAll: Future[Seq[E]] = {
    db.run(getAllQuery.result)
  }

  def getById(id: Long): Future[E] = {
    db.run(getByIdQuery(id).result.head)
  }

  def filter[C <: Rep[_]](expr: T => C)(implicit wt: CanBeQueryCondition[C]) = {
    db.run(filterQuery(expr).result)
  }

  def save(row: E) = {
    db.run(saveQuery(row))
  }

  def updateById(id: Long, row: E) = {
    db.run(updateByIdQuery(id, row))
  }

  def deleteById(id: Long) = {
    db.run(deleteByIdQuery(id))
  }
}
