package dao.impl

import javax.inject.Inject
import dao.AdminDAO
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.PostgresDriver

/**
  * Created by khanguyen on 3/13/16.
  */
class AdminDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[PostgresDriver]
    with AdminDAO {

  import driver.api._
  import schema.AdminsTable.adminTableQuery
  import models.Admin

  override def getAdmin(email: String) = db.run(adminTableQuery.filter(_.email === email).result.headOption)

  override def isAdminExists() = db.run(adminTableQuery.exists result)

  override def createAdmin(admin: Admin) = db.run(adminTableQuery returning adminTableQuery.map(_.id) += admin)

}
