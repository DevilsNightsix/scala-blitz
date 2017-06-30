package models

import slick.jdbc.PostgresProfile.api._

trait BaseModel {
  val id: Option[Long]
}

abstract class BaseTable[E](tag: Tag, tableName: String)
  extends Table[E](tag, tableName) {

  val id: Rep[Long] = column[Long]("Id", O.PrimaryKey, O.AutoInc)
}
