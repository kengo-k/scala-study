package example

// scala2
package implicit_def {
  object StringToInt {
    implicit def stringtoInt(s: String): Int = Integer.parseInt(s)
  }
}

// scala3
package implicit_def2 {
  object StringToInt {
    given Conversion[String, Int] with
      def apply(s: String): Int = Integer.parseInt(s)
  }
}

class ImplicitConvSpec extends munit.FunSuite {

  test("String to Int(implicit)") {
    import implicit_def.StringToInt.stringtoInt
    val x: Int = "123"
    assertEquals(x, 123)
  }

  test("String to Int(given)") {
    import implicit_def2.StringToInt.given
    val x: Int = "123"
    assertEquals(x, 123)
  }

}
