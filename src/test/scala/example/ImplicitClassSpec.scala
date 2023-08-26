package example

// scala2
package implicit_class2 {
  implicit class IntExtension(val i: Int) {
    def isEven = i % 2 == 0
  }
}

// scala3
package implicit_class3 {
  object IntExtension {
    extension (i: Int) def isEven = i % 2 == 0

  }
}

class ImplicitClassSpec extends munit.FunSuite {

  test("isEven(scala2)") {
    import implicit_class2.IntExtension
    assertEquals(2.isEven, true)
    assertEquals(3.isEven, false)
  }

  test("isEven(scala3)") {
    import implicit_class3.IntExtension._
    assertEquals(2.isEven, true)
    assertEquals(3.isEven, false)
  }

}
