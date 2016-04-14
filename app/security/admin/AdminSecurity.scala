package security.admin

/**
  * Created by khanguyen on 3/13/16.
  */
trait AdminSecurity {
  import play.api.mvc.Security.AuthenticatedBuilder
  import play.api.mvc._

  object SecuredAction extends AuthenticatedBuilder(userInfo, onUnauthorized)

  def authorize(email: String, redirect: Call) = Results.Redirect(redirect).withSession(adminHeader -> email)

  val adminHeader = "Admin-Email"

  private def userInfo(requestHeader: RequestHeader): Option[String] =
    requestHeader.session.get(adminHeader)

  private def onUnauthorized(requestHeader: RequestHeader): Result =
    Results.Redirect(controllers.admin.routes.AuthController.loginForm)

}

