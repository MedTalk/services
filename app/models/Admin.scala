package models

import com.github.t3hnar.bcrypt._

/**
  * Created by khanguyen on 3/13/16.
  */
case class Admin(name: String, email: String, private val hashedPassword: String, id: Option[Long]) {

  def validate(password: String): Boolean = password.isBcrypted(hashedPassword)

}
