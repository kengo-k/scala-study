package fpinscala

class EX2_1 extends munit.FunSuite {

  // Simple Implementation Following the Fibonacci Sequence Definition
  // However, this implementation is not tail-recursive
  def fib_simple(n: Int): Int = {
    n match {
      case 0 => 0
      case 1 => 1
      case _ => fib_simple(n - 2) + fib_simple(n - 1)
    }
  }

  // Efficient Implementation Using Tail Recursion
  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, curr: Int): Int = {
      if (n <= 0) prev
      else loop(n - 1, curr, prev + curr)
    }
    loop(n, 0, 1)
  }

  test("fib(0)") {
    assertEquals(fib_simple(0), 0)
    assertEquals(fib(0), 0)
  }

  test("fib(1)") {
    assertEquals(fib_simple(1), 1)
    assertEquals(fib(1), 1)
  }

  test("fib(2)") {
    assertEquals(fib_simple(2), 1)
    assertEquals(fib(2), 1)
  }

  test("fib(3)") {
    assertEquals(fib_simple(3), 2)
    assertEquals(fib(3), 2)
  }

  test("fib(4)") {
    assertEquals(fib_simple(4), 3)
    assertEquals(fib(4), 3)
  }

  test("fib(5)") {
    assertEquals(fib_simple(5), 5)
    assertEquals(fib(5), 5)
  }

  test("fib(6)") {
    assertEquals(fib_simple(6), 8)
    assertEquals(fib(6), 8)
  }

  test("fib(25)") {
    assertEquals(fib_simple(25), fib_simple(24) + fib_simple(23))
    assertEquals(fib(25), fib(24) + fib(23))
  }
}
