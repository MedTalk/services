package security.app

/**
  * Created by khanguyen on 4/14/16.
  */
case class AppCredential(email: String, password: String)

object AppCredential {

  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val appCredentialReads: Reads[AppCredential] = (
    (__ \ "email").read[String] ~
      (__ \ "password").read[String]
    ) (AppCredential.apply _)
}
