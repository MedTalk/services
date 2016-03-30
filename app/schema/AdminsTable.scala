package schema

import models.Admin
import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by khanguyen on 3/13/16.
  */
class AdminsTable(tag: Tag) extends Table[Admin](tag, "admins") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name", O.Length(255))

  def email = column[String]("email", O.Length(255))

  def passwordHash = column[String]("password_hash", O.Length(255))

  override def * : ProvenShape[Admin] = (name, email, passwordHash, id.?) <>(Admin.tupled, Admin.unapply _)

  def uniqEmailIdx = index("admins_uniq_email", email, true)
}

object AdminsTable {
  val adminTableQuery = TableQuery[AdminsTable]
}

