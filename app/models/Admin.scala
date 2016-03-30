package models

import com.github.t3hnar.bcrypt._

/**
  * Created by khanguyen on 3/13/16.
  */
case class Admin(name: String, email: String, private val passwordHash: String, id: Option[Long] = None) {

  def validate(password: String): Boolean = password.isBcrypted(passwordHash)

  def changePassword(password: String): Admin = Admin(name, email, password.bcrypt, id)

}
