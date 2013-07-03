import com.escalatesoft.subcut.inject._
import config.StandardConfiguration
import play.api.GlobalSettings
import services.{TextGenerator, WelcomeTextGenerator}

/**
 * Set up the Subcut binding module and provide the mechanism for return objects from the dependency graph.
 */
object Global extends GlobalSettings {

  object Context extends Injectable {
    implicit val bindingModule = StandardConfiguration  // use the standard config by default

    val application = inject[controllers.Application]
    val applicationClass = classOf[controllers.Application]
  }

  /**
   * Controllers must be resolved through the bindings. There is a special method of GlobalSettings
   * that we can override to resolve a given controller. This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = {

    controllerClass match {
      case Context.applicationClass => Context.application.asInstanceOf[A]
      case _ => throw new IllegalArgumentException

    }
  }
}
