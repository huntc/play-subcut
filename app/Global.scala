import com.escalatesoft.subcut.inject.{BindingModule, Injectable, BindingId, NewBindingModule}
import play.api.GlobalSettings
import services.{TextGenerator, WelcomeTextGenerator}

/**
 * Set up the Subcut binding module and provide the mechanism for return objects from the dependency graph.
 */
object Global extends GlobalSettings {

  import NewBindingModule._

  object Context extends Injectable {
    implicit val bindingModule: BindingModule = newBindingModule {
      module =>
        import module._

        bind[TextGenerator] toSingle (new WelcomeTextGenerator)
        bind[controllers.Application] toSingle (new controllers.Application)
    }

    val application = inject[controllers.Application]
  }

  /**
   * Controllers must be resolved through the bindings. There is a special method of GlobalSettings
   * that we can override to resolve a given controller. This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    val applicationClass = classOf[controllers.Application]

    controllerClass match {
      case `applicationClass` => Context.application.asInstanceOf[A]
      case _ => throw new IllegalArgumentException

    }
  }
}
