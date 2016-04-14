package models

import com.github.t3hnar.bcrypt._


/**
  * Created by khanguyen on 3/30/16.
  */
case class User(name: String, email: String, private val passwordHash: String, roleID: Int, phoneNumber: Option[String], id: Option[Long] = None) {

  def validatePassword(password: String): Boolean = password.isBcrypted(passwordHash)

  def changePassword(password: String): User = this.copy(passwordHash = password.bcrypt)

}

object User {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val userReads: Reads[User] = (
    (__ \ "name").read[String] ~
      (__ \ "email").read[String] ~
      (__ \ "passwordHash").read[String] ~
      (__ \ "roleID").read[Int] ~
      (__ \ "phoneNumber").readNullable[String] ~
      (__ \ "id").readNullable[Long]
    ) (User.apply _)

  implicit val userWrites: Writes[User] = (
    (__ \ "name").write[String] ~
      (__ \ "email").write[String] ~
      (__ \ "passwordHash").write[String] ~
      (__ \ "roleID").write[Int] ~
      (__ \ "phoneNumber").writeNullable[String] ~
      (__ \ "id").writeNullable[Long]
    ) (unlift(User.unapply))
}
