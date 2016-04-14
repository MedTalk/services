package dao.impl

import javax.inject.Inject

import dao.AppDAO
import models.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.PostgresDriver

/**
  * Created by khanguyen on 4/14/16.
  */
class AppDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[PostgresDriver] with AppDAO {

  import driver.api._
  import schema.UsersTable.usersTableQuery

  override def getUser(email: String) = db.run(usersTableQuery.filter(_.email === email).result.headOption)

  override def createUser(user: User) = db.run(usersTableQuery += user)
}
