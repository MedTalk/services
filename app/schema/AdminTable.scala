package schema

import models.Admin
import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by khanguyen on 3/13/16.
  */
class AdminTable(tag: Tag) extends Table[Admin](tag, "admins") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name", O.Length(255))

  def email = column[String]("email", O.Length(255))

  def hashedPassword = column[String]("hashed_password", O.Length(255))

  override def * : ProvenShape[Admin] = (name, email, hashedPassword, id.?) <>(Admin.tupled, Admin.unapply _)

  def uniqEmailIdx = index("admins_uniq_email", email, true)
}

object AdminTable {
  val adminTableQuery = TableQuery[AdminTable]
}

