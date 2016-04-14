package security.app

import play.api.mvc.Security.AuthenticatedBuilder

/**
  * Created by khanguyen on 4/14/16.
  */
trait AppSecurity {

  import play.api.mvc.RequestHeader
  import pdi.jwt._
  import models.User
  import play.api.mvc.{Result, Results}

  object AppSecuredAction extends AuthenticatedBuilder(userInfo, onUnauthorized)

  def authorize(user: User)(implicit request: RequestHeader): Result = Results.Ok.addingToJwtSession(appHeader, user)

  private val appHeader = "user"

  private def userInfo(request: RequestHeader): Option[User] = request.jwtSession.getAs[User](appHeader)

  private def onUnauthorized(request: RequestHeader): Result = Results.Unauthorized
}
