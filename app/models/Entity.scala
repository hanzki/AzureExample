package models

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by hanzki on 13/08/16.
  */
case class Entity(
  id: Option[Int],
  value: String
)

object Entity {

  val entityForm = Form(
    mapping(
      "id" -> optional(number),
      "value" -> nonEmptyText
    )(Entity.apply)(Entity.unapply)
  )

}
