package controllers

import play.api.mvc._
import services.{WelcomeTextGenerator, TextGenerator}
import com.escalatesoft.subcut.inject.{BindingModule, Injectable}

/**
 * Instead of declaring an object of Application as per the template project, we must declare a class given that
 * that Subcut is going to be responsible for creating it and wiring it up with the text generator service.
 */
class Application(implicit val bindingModule: BindingModule) extends Controller with Injectable {

  // injectOptional provides safe interception of bindings, but falling back on a default implementation otherwise
  val textGenerator = injectOptional [TextGenerator] getOrElse new WelcomeTextGenerator

  def index = Action {
    Ok(views.html.index(textGenerator.welcomeText))
  }

}
