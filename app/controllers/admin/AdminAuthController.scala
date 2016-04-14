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
class AdminAuthController @Inject()(val messagesApi: MessagesApi,
                                    adminDAO: AdminDAO)(implicit ec: ExecutionContext)
  extends Controller
    with AdminSecurity
    with I18nSupport {

  val form = AdminCredential.form

  def loginForm = Action { request =>
    request.session.get(adminHeader) match {
      case None => Ok(views.html.admin.login(form))
      case Some(_) => Redirect(routes.AdminController.index)
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
            Redirect(routes.AdminAuthController.loginForm) // TODO: flash admin not found
          case Some(admin) =>
            if (admin.validatePassword(adminCredential.password)) {
              authorize(adminCredential.email, routes.AdminController.index)
            } else {
              Redirect(routes.AdminAuthController.loginForm) // TODO: flash incorrect password
            }
        }
      }
    )
  }

  def logout = Action {
    // TODO: flash logout success
    Redirect(routes.AdminAuthController.loginForm).withNewSession
  }

  def changePasswordForm = TODO

  def changePassword(password: String) = TODO
}
