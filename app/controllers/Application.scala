package controllers

import javax.inject.Inject

import models.{DatabaseDao, Entity}
import play.api.Configuration
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.json.{Format, Json}
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global


class Application @Inject() (dao: DatabaseDao, configuration: Configuration, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  implicit val entityFormat: Format[Entity] = Json.format[Entity]

  def index = Action.async {
    dao.all().map( entities =>
      Ok(views.html.index(entities, Entity.entityForm))
    )
  }

  def insert = Action.async { implicit req =>

    val entity = Entity.entityForm.bindFromRequest().get

    dao.insert(entity).map(_ => Redirect(routes.Application.index()))
  }

}
