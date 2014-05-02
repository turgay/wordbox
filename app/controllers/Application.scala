package controllers

import play.api._
import play.api.data.Forms.longNumber
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.mvc._
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.Flash

object Application extends Controller {
  def index = Action {
     Redirect(routes.Words.search())
  }
  
  def about = Action { implicit request =>
      Ok(views.html.about(request.flash))
  }
  
  def contact = Action {implicit request =>
      Ok(views.html.contact(request.flash))
  }
  def feedback = Action {implicit request =>
      Ok(views.html.contact(request.flash))
  }

}