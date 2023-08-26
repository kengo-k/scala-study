package example

package logging {
  case class LogFormat(prefix: String)

  object Defaults2 {
    implicit val logFormat: LogFormat = LogFormat("[INFO]")
    implicit val debugFormat: LogFormat = LogFormat("[DEBUG]")
  }

  object Defaults3 {
    given logFormat: LogFormat = LogFormat("[INFO]")
    given debugFormat: LogFormat = LogFormat("[DEBUG]")
  }
}

class ImplicitParamSpec2 extends munit.FunSuite {

  import logging.LogFormat
  import logging.Defaults2.logFormat

  def log(message: String)(implicit logFormat: LogFormat): String = {
    s"${logFormat.prefix} $message"
  }

  test("log(scala2)") {
    assertEquals(log("Hello"), "[INFO] Hello")
  }

  test("debug(scala2)") {
    implicit val debugFormat: LogFormat = logging.Defaults2.debugFormat
    assertEquals(log("Hello"), "[DEBUG] Hello")
  }

}

class ImplicitParamSpec3 extends munit.FunSuite {

  import logging.LogFormat
  import logging.Defaults3.logFormat

  def log(message: String)(using logFormat: LogFormat): String = {
    s"${logFormat.prefix} $message"
  }

  test("log(scala3)") {
    assertEquals(log("Hello"), "[INFO] Hello")
  }

  test("debug(scala3)") {
    given debugFormat: LogFormat = logging.Defaults3.debugFormat
    assertEquals(log("Hello"), "[DEBUG] Hello")
  }

}
