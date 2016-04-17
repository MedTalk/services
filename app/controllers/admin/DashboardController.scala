package controllers.admin

import play.api.mvc.Controller
import security.admin.AdminSecurity

/**
  * Created by khanguyen on 3/13/16.
  */
class DashboardController extends Controller with AdminSecurity {

  def index = AdminSecuredAction {
    Ok(views.html.admin.dashboard.index())
  }

}
