package fpinscala

class EX2_2 extends munit.FunSuite {

  def LE(a: Int, b: Int): Boolean = {
    a <= b
  }

  // my initial answer
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean = {
      if (n >= as.length) {
        return true
      } else {
        if (ordered(as(n - 1), as(n))) {
          return loop(n + 1)
        } else {
          return false
        }
      }
    }
    if (as.length <= 1) {
      return true
    }
    loop(1)
  }

  // answer
  def isSortedAnswer[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean =
      if n >= as.length - 1 then true
      else if gt(as(n), as(n + 1)) then false
      else go(n + 1)

    go(0)
  }

  test("1 <= 2") {
    assertEquals(LE(1, 2), true)
  }

  test("1 <= 1") {
    assertEquals(LE(1, 1), true)
  }

  test("2 <= 1") {
    assertEquals(LE(2, 1), false)
  }

  test("isSorted([])") {
    assertEquals(isSorted(Array[Int](), LE), true)
  }

  test("isSorted([1])") {
    assertEquals(isSorted(Array[Int](1), LE), true)
  }

  test("isSorted([1,2])") {
    assertEquals(isSorted(Array[Int](1, 2), LE), true)
  }

  test("isSorted([1,2,3])") {
    assertEquals(isSorted(Array[Int](1, 2, 3), LE), true)
  }

  test("isSorted([1,3,2])") {
    assertEquals(isSorted(Array[Int](1, 3, 2), LE), false)
  }
}
