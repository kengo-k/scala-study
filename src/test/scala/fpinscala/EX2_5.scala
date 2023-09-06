package fpinscala

class EX2_5 extends munit.FunSuite {

  def sqrt(a: Int): Int = a * a
  def toString(a: Int): String = String.valueOf(a)

  def compose[A, B, C](f: B => C, g: A => B): A => C = a => f(g(a))

  test("toString compose sqrt") {
    val f = compose(toString, sqrt)
    assertEquals(f(10), "100")
  }
}
