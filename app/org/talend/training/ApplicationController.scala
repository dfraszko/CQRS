package org.talend.training

import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._

case class Data(name: String, info: String)

class ApplicationController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  implicit val dataWrites = Json.writes[Data]

  def index = Action {

    //val result = Json.obj("name" -> "toto", "info" -> "yo")
    val result = Data("toto", "yo")
    Ok(Json.toJson(result))
  }

}
