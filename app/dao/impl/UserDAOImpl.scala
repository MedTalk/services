package dao.impl

import javax.inject.Inject

import dao.UserDAO
import models.User

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.PostgresDriver

import scala.concurrent.Future

/**
  * Created by khanguyen on 3/30/16.
  */
class UserDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[PostgresDriver]
    with UserDAO {

  import schema.UsersTable.usersTable
  import driver.api._

  override def userExists(email: String): Future[Boolean] = db.run(usersTable.filter(_.email === email).exists result)

  override def getUser(email: String): Future[Option[User]] = db.run(usersTable.filter(_.email === email).result.headOption)

}
