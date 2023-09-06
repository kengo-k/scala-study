package fpinscala

class EX2_4 extends munit.FunSuite {

  def add(a: Int): Int => Int = b => a + b

  // my initial answer
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)

  test("add(1)(2)") {
    assertEquals(add(1)(2), 3)
  }

  test("uncurry(add)(1,2)") {
    assertEquals(uncurry(add)(1, 2), 3)
  }
}
