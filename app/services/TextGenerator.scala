package services

/**
 * A type declaring the interface that will be injectable.
 */
abstract class TextGenerator(val welcomeText: String)

/**
 * A standard implementation of TextGenerator that we will inject.
 */
class WelcomeTextGenerator extends TextGenerator("Your new application is ready.")