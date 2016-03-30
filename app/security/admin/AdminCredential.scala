package security.admin

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by khanguyen on 3/13/16.
  */
case class AdminCredential(email: String, password: String)

object AdminCredential {
  val form = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText
    )(AdminCredential.apply)(AdminCredential.unapply)
  )
}
