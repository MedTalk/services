package security.app

import models.User

/**
  * Created by khanguyen on 4/14/16.
  */
case class AppSignup(name: String, email: String, password: String, roleID: Int, phoneNumber: Option[String]) {
  lazy val user = User(name, email, "", roleID, phoneNumber).changePassword(password)
}

object AppSignup {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val appSignupReads: Reads[AppSignup] = (
    (__ \ "name").read[String] ~
      (__ \ "email").read[String](Reads.email) ~
      (__ \ "password").read[String] ~
      (__ \ "roleID").read[Int] ~
      (__ \ "phoneNumber").readNullable[String]
    ) (AppSignup.apply _)
}
