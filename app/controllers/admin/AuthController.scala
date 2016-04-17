package controllers.admin

import javax.inject.Inject

import dao.AdminDAO
import play.api.i18n.{MessagesApi, I18nSupport}
import play.api.mvc.{Action, Controller}
import security.admin.{AdminSecurity, AdminCredential}

import scala.concurrent.{Future, ExecutionContext}

/**
  * Created by khanguyen on 3/13/16.
  */
class AuthController @Inject()(val messagesApi: MessagesApi,
                               adminDAO: AdminDAO)(implicit ec: ExecutionContext)
  extends Controller
    with AdminSecurity
    with I18nSupport {

  val form = AdminCredential.form

  def loginForm = Action { request =>
    request.session.get(adminHeader) match {
      case None => Ok(views.html.admin.login(form))
      case Some(_) => Redirect(routes.DashboardController.index)
    }
  }

  def login = Action.async(parse.urlFormEncoded) { implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(BadRequest(views.html.admin.login(formWithErrors)))
      },
      adminCredential => {
        adminDAO getAdmin adminCredential.email map {
          case None =>
            Redirect(routes.AuthController.loginForm).flashing("notice" -> "Credential not found")
          case Some(admin) =>
            if (admin.validatePassword(adminCredential.password)) {
              authorize(adminCredential.email, routes.DashboardController.index)
            } else {
              Redirect(routes.AuthController.loginForm).flashing("notice" -> "Invalid credential")
            }
        }
      }
    )
  }

  def logout = Action {
    Redirect(routes.AuthController.loginForm).withNewSession.flashing("notice" -> "You have been logged out.")
  }

  def changePasswordForm = TODO

  def changePassword(password: String) = TODO
}
