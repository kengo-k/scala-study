package fpinscala

class EX2_3 extends munit.FunSuite {

  // my initial answer
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = { a =>
    { b =>
      f(a, b)
    }
  }

  // answer
  def curryAnswer[A, B, C](f: (A, B) => C): A => (B => C) =
    a => b => f(a, b)

  def add(a: Int, b: Int) = a + b

  test("add(1,2)") {
    assertEquals(add(1, 2), 3)
  }

  test("curry(add)(1)(2)") {
    assertEquals(curry(add)(1)(2), 3)
  }

  test("curry(add)(10)") {
    val add10 = curry(add)(10)
    assertEquals(add10(1), 11)
    assertEquals(add10(5), 15)
  }

}
