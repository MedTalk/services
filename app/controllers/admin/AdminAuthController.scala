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

  val loginForm = AdminCredential.loginForm

  def loginView = Action { request =>
    request.session.get(adminHeader) match {
      case None => Ok(views.html.admin.login(loginForm))
      case Some(_) => Redirect(routes.AdminController.index)
    }
  }

  def login = Action.async(parse.urlFormEncoded) { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(BadRequest(views.html.admin.login(formWithErrors)))
      },
      adminCredential => {
        adminDAO getAdmin adminCredential.email map {
          case None =>
            Redirect(routes.AdminAuthController.loginView) // admin not found
          case Some(admin) =>
            if (admin.validate(adminCredential.password)) {
              Redirect(routes.AdminController.index).withSession(adminHeader -> adminCredential.email)
            } else {
              Redirect(routes.AdminAuthController.loginView) // incorrect password
            }
        }

      }
    )
  }

  def logout = Action {
    Redirect(routes.AdminAuthController.loginView).withNewSession
  }
}
