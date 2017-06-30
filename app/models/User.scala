package models

import slick.jdbc.PostgresProfile.api._

case class User(id: Option[Long], username: String, email: String) extends BaseModel

class Users(tag: Tag) extends BaseTable[User](tag, "users") {
  def * = (id.?, username, email) <> (User.tupled, User.unapply)

  def username: Rep[String] = column[String]("username", O.Length(150, varying = true))
  def email: Rep[String] = column[String]("email", O.Length(150, varying = true))
  override val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
}
