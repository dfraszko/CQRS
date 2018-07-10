package org.talend.training

import javax.inject.Inject
import play.api.mvc._

class ApplicationController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  def index = Action {
    Ok
  }

}
