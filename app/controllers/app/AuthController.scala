package controllers.app

import javax.inject.Inject

import dao.AppDAO
import play.api.libs.json.{JsError, JsSuccess}
import play.api.mvc.{Action, Controller}
import security.app.{AppCredential, AppSecurity, AppSignup}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by khanguyen on 4/14/16.
  */
class AuthController @Inject()(appDAO: AppDAO)(implicit ec: ExecutionContext) extends Controller with AppSecurity {

  def login = Action.async(parse.json) { implicit request =>
    request.body.validate[AppCredential] match {
      case JsError(_) => Future.successful(BadRequest)
      case JsSuccess(credential, _) =>
        appDAO getUser credential.email map {
          case None => NotFound
          case Some(user) =>
            if (user.validatePassword(credential.password)) {
              authorize(user)
            } else {
              Unauthorized
            }
        }
    }
  }

  def signup = Action.async(parse.json) { implicit request =>
    request.body.validate[AppSignup] match {
      case JsError(_) => Future.successful(BadRequest)
      case JsSuccess(signup, _) =>
        appDAO createUser signup.user map {
          case 1 => Created
          case _ => Conflict
        }
    }
  }

}
