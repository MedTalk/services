package controllers.xmpp

import javax.inject.Inject

import dao.ExtAuthDAO
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext

/**
  * Created by khanguyen on 3/30/16.
  */
class ExternalAuthController @Inject()(extAuthDAO: ExtAuthDAO)(implicit ec: ExecutionContext) extends Controller {

  def auth(email: String, password: String) = Action.async {
    extAuthDAO getUser email map {
      case None => NotFound
      case Some(user) =>
        if (user.validatePassword(password)) {
          Unauthorized
        } else {
          NoContent
        }
    }
  }

  def isUser(email: String) = Action.async {
    extAuthDAO userExists email map {
      case true => NoContent
      case false => NotFound
    }
  }

  def setPassword(password: String) = Action {
    Forbidden
  }

}
