package dao

import com.google.inject.ImplementedBy
import dao.impl.AdminDAOImpl
import models.Admin

import scala.concurrent.Future

/**
  * Created by khanguyen on 3/13/16.
  */
@ImplementedBy(classOf[AdminDAOImpl])
trait AdminDAO {
  def getAdmin(email: String): Future[Option[Admin]]
}
