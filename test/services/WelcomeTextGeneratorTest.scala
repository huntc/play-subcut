package services

import org.specs2.mutable._
import com.escalatesoft.subcut.inject.NewBindingModule._

/**
  * Unit tests for the service itself. We would expect that the majority of unit tests would be on components like
  * this.
  */
class WelcomeTextGeneratorTest extends Specification {

  implicit val testConfiguration = newBindingModule {
    module =>
  }

   "WelcomeTextGenerator" should {

     "generate some text" in {
       val textGenerator = new WelcomeTextGenerator

       textGenerator.welcomeText mustEqual "Your new application is ready."
     }
   }
 }