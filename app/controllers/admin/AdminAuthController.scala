package controllers.admin

import javax.inject.Inject

import play.api.i18n.{MessagesApi, I18nSupport}
import play.api.mvc.{Action, Controller}
import security.admin.{AdminSecurity, AdminCredential}

/**
  * Created by khanguyen on 3/13/16.
  */
class AdminAuthController @Inject()(val messagesApi: MessagesApi) extends Controller with AdminSecurity with I18nSupport {

  val loginForm = AdminCredential.loginForm

  def loginView = Action { request =>
    request.session.get(adminHeader) match {
      case None => Ok(views.html.admin.login(loginForm))
      case Some(_) => Redirect(routes.AdminController.index)
    }
  }

  def login = Action(parse.urlFormEncoded) { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.admin.login(formWithErrors))
      },
      adminCredential => {
        // TODO: verify credential
        Redirect(routes.AdminController.index).withSession(adminHeader -> adminCredential.email)
      }
    )
  }

  def logout = Action {
    Redirect(routes.AdminAuthController.loginView).withNewSession
  }
}
