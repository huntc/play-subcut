package services

import com.escalatesoft.subcut.inject.{BindingModule, Injectable}

/**
 * A type declaring the interface that will be injectable.
 */
abstract class TextGenerator(val welcomeText: String)

/**
 * A simple implementation of TextGenerator that we will inject.
 */
class WelcomeTextGenerator(implicit val bindingModule: BindingModule)
  extends TextGenerator("Your new application is ready.") with Injectable