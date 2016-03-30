package models

import com.github.t3hnar.bcrypt._

/**
  * Created by khanguyen on 3/30/16.
  */
case class User(name: String, email: String, private val passwordHash: String, roleID: Int, phoneNumber: Option[String], id: Option[Long] = None) {

  def validatePassword(password: String): Boolean = password.isBcrypted(passwordHash)

  def changePassword(password: String): User = this.copy(passwordHash = password.bcrypt)

}
