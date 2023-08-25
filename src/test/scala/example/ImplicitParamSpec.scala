package example

package logging {
  case class LogFormat(prefix: String)

  object Defaults {
    implicit val logFormat: LogFormat = LogFormat("[INFO]")
    implicit val debugFormat: LogFormat = LogFormat("[DEBUG]")
  }
}

class ImplicitParamSpec extends munit.FunSuite {

  import logging.LogFormat
  import logging.Defaults.logFormat

  def log(message: String)(implicit logFormat: LogFormat): String = {
    s"${logFormat.prefix} $message"
  }

  test("log") {
    assertEquals(log("Hello"), "[INFO] Hello")
  }

  test("log with implicit parameter") {
    assertEquals(log("Hello")(logging.Defaults.debugFormat), "[DEBUG] Hello")
  }

}
