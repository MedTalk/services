package schema

import models.User
import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by khanguyen on 3/30/16.
  */
class UsersTable(tag: Tag) extends Table[User](tag, "users") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name", O.Length(255))

  def email = column[String]("email", O.Length(255))

  def passwordHash = column[String]("password_hash", O.Length(255))

  def roleID = column[Int]("role")

  def phoneNumber = column[Option[String]]("phone_number", O.Length(255))

  override def * : ProvenShape[User] = (name, email, passwordHash, roleID, phoneNumber, id.?) <>((User.apply _).tupled, User.unapply)

  def uniqEmailIdx = index("users_uniq_email", email, true)
}

object UsersTable {
  val usersTableQuery = TableQuery[UsersTable]
}
