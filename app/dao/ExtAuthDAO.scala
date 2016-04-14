package dao

import com.google.inject.ImplementedBy
import dao.impl.ExtAuthDAOImpl
import models.User

import scala.concurrent.Future

/**
  * Created by khanguyen on 3/30/16.
  */
@ImplementedBy(classOf[ExtAuthDAOImpl])
trait ExtAuthDAO {

  def userExists(email: String): Future[Boolean]

  def getUser(email: String): Future[Option[User]]
}
