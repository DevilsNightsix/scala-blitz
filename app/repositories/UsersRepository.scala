package repositories

import javax.inject._

import models.{User, Users}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.PostgresProfile.api._

class UsersRepository @Inject()(
  override protected val dbConfigProvider: DatabaseConfigProvider
) extends BaseRepository[Users, User](dbConfigProvider) {
  override val query = TableQuery[Users]
}
