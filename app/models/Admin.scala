package models

import com.github.t3hnar.bcrypt._

/**
  * Created by khanguyen on 3/13/16.
  */
case class Admin(name: String, email: String, private val passwordHash: String, id: Option[Long] = None) {

  def validatePassword(password: String): Boolean = password.isBcrypted(passwordHash)

  def changePassword(password: String): Admin = this.copy(passwordHash = password.bcrypt)

}
