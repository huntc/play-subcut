package controllers

import org.specs2.mock.Mockito
import org.specs2.mutable._

import play.api.test._
import services.{WelcomeTextGenerator, TextGenerator}
import com.escalatesoft.subcut.inject.{Injectable, NewBindingModule}
import config.StandardConfiguration

/**
 * We focus here on testing the controller only - not the infrastructure in front or behind it. Using dependency
 * injection allows the application controller to become testable. It is conceivable that you might have a unit
 * test for the controller if there is enough logic contained in the method that makes it worth testing - the
 * integration test might offer a more useful test if there is not given that you are then testing that the
 * route is configured properly.
 */
class ApplicationTest extends Specification with Mockito {

  "Application" should {

    "invoke the text generator" in {
      // grab a mutable copy of the standard bindings and change 'em for the test.
      StandardConfiguration.modifyBindings { implicit module =>
        import module._
        val textGenerator = mock[TextGenerator]
        bind [TextGenerator] toSingle textGenerator  // override the real generator with the mock

        // set the script for the mock generator
        textGenerator.welcomeText returns ""

        val application = new controllers.Application  // uses the implicit module
        application.index(FakeRequest())

        // check the calls to the mock
        there was one(textGenerator).welcomeText
      }

    }
  }
}
