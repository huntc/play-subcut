package config

import com.escalatesoft.subcut.inject._
import services.{WelcomeTextGenerator, TextGenerator}

/**
 * Created with IntelliJ IDEA.
 * User: dick
 * Date: 7/3/13
 * Time: 1:52 PM
 * Standard configuration for subcut
 */
object StandardConfiguration extends NewBindingModule({ module =>
  import module._

  bind [TextGenerator] toSingle new WelcomeTextGenerator

  // controllers.Application is itself injectable, so we need the toModuleSingle form to pay the config forward
  bind [controllers.Application] toModuleSingle { implicit module => new controllers.Application }
})
