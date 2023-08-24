package example

class ImplicitParamSpec extends munit.FunSuite {

  case class LogFormat(prefix: String)

  implicit val logFormat: LogFormat = LogFormat("[INFO]")

  def log(message: String)(implicit logFormat: LogFormat): String = {
    s"${logFormat.prefix} $message"
  }

  test("log") {
    assertEquals(log("Hello"), "[INFO] Hello")
  }

}
