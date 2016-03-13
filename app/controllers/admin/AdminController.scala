package controllers.admin

import play.api.mvc.Controller
import security.admin.AdminSecurity

/**
  * Created by khanguyen on 3/13/16.
  */
class AdminController extends Controller with AdminSecurity {

  def index = SecuredAction {
    Ok(views.html.admin.index())
  }

}
