package security.admin

import play.api.mvc.Security.AuthenticatedBuilder
import play.api.mvc._

/**
  * Created by khanguyen on 3/13/16.
  */
trait AdminSecurity {
  val adminHeader = "admin-id"

  private def userInfo(requestHeader: RequestHeader): Option[String] =
    requestHeader.session.get(adminHeader)

  private def onUnauthorized(requestHeader: RequestHeader): Result =
    Results.Redirect(controllers.admin.routes.AdminAuthController.loginView)

  object SecuredAction extends AuthenticatedBuilder(userInfo, onUnauthorized)

}

