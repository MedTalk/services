package dao

import com.google.inject.ImplementedBy
import dao.impl.AppDAOImpl

/**
  * Created by khanguyen on 4/14/16.
  */
@ImplementedBy(classOf[AppDAOImpl])
trait AppDAO {

  import models.User
  import scala.concurrent.Future

  def getUser(email: String): Future[Option[User]]

  def createUser(user: User): Future[Int]
}
