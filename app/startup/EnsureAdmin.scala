package startup

import javax.inject.Inject

import dao.AdminDAO
import models.Admin
import play.api.Logger

import com.github.t3hnar.bcrypt._

import scala.concurrent.ExecutionContext

/**
  * Created by khanguyen on 3/30/16.
  */
class EnsureAdmin @Inject()(adminDAO: AdminDAO)(implicit ec: ExecutionContext) {
  val logger = Logger(getClass)

  adminDAO.isAdminExists() map {
    case false =>
      val password = "changeme" // TODO: create random password
      val admin = Admin("admin", "admin@email.com", password.bcrypt)
      adminDAO.createAdmin(admin) map { _ =>
        logger.warn(
          s"""
             |An admin was created with
             |email: ${admin.email}
             |password: ${password}
           """.stripMargin)
      }
    case _ => logger.info("Admin table not empty. No need to create first admin.")
  }
}
