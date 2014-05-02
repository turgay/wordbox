package controllers

import play.api._
import play.api.mvc._
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import play.api.i18n.Messages
import play.api.mvc.Flash
import models.Word

object Words extends Controller {
   
  def newSearch = Action {implicit request =>
    Ok(views.html.search(null,null))
  }
  
  def search(page: Int, word : String) = Action { implicit request =>
    Ok(views.html.search(Word.list(page = page, word = word), word))
   }
}