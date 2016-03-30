package schema

import models.Role
import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by khanguyen on 3/30/16.
  */
class RolesTable(tag: Tag) extends Table[Role](tag, "roles") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name", O.Length(255))

  override def * : ProvenShape[Role] = (name, id.?) <>(Role.tupled, Role.unapply)

  def uniqNameIdx = index("roles_uniq_name", name, true)
}

object RolesTable {
  val rolesTable = TableQuery[RolesTable]
}
