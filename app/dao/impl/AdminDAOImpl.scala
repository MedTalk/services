package dao.impl

import javax.inject.Inject

import dao.AdminDAO
import models.Admin
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.PostgresDriver

import scala.concurrent.Future


/**
  * Created by khanguyen on 3/13/16.
  */
class AdminDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[PostgresDriver]
    with AdminDAO {

  import driver.api._
  import schema.AdminsTable.adminTableQuery

  override def getAdmin(email: String): Future[Option[Admin]] =
    db.run(adminTableQuery.filter(_.email === email).result.headOption)

  override def isAdminExists(): Future[Boolean] = db.run(adminTableQuery.exists result)

  override def createAdmin(admin: Admin): Future[Long] = db.run(adminTableQuery returning adminTableQuery.map(_.id) += admin)
}
