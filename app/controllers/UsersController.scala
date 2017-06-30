package controllers

import scala.concurrent.{ ExecutionContext, Future }

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

import models.User
import repositories.UsersRepository

@Singleton
class UsersController @Inject()(cc: ControllerComponents, usersRepo: UsersRepository)(implicit exec: ExecutionContext) extends AbstractController(cc) {

  implicit val userReads: Reads[User] = Json.reads[User]
  implicit val userWrites: Writes[User] = Json.writes[User]

  def create() = Action.async(BodyParsers.parse.json) { implicit request =>
    val userResult = request.body.validate[User]

    userResult.fold(
      errors => {
        Future { BadRequest(Json.obj("status" -> "Unprocessable Entity", "errors" -> JsError.toJson(errors))) }
      },
      user => {
        for {
          savedUser <- usersRepo.save(user)
        } yield Ok(Json.toJson(savedUser))
      }
    )

  }

  def show(id: Long) = Action.async { implicit request =>
    usersRepo.getById(id) map {
      user => Ok(Json.toJson(user))
    } recover {
      case _ =>  NotFound
    }
  }

  def index = Action.async { implicit request =>
    for {
      users <- usersRepo.getAll
    } yield Ok(Json.toJson(users))
  }
}
