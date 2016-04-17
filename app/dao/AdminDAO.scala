package dao

import com.google.inject.ImplementedBy
import dao.impl.AdminDAOImpl
import scala.concurrent.Future

/**
  * Created by khanguyen on 3/13/16.
  */
@ImplementedBy(classOf[AdminDAOImpl])
trait AdminDAO {

  import models.Admin

  def getAdmin(email: String): Future[Option[Admin]]

  def isAdminExists(): Future[Boolean]

  def createAdmin(admin: Admin): Future[Long]
}
