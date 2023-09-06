package fpinscala

import java.{util => ju}

class EX3_3 extends munit.FunSuite {
  // my initial answer
  def setHead[A](newX: A, l: List[A]): List[A] = l match {
    case x :: xs => newX :: xs
    case Nil     => sys.error("setHead on empty list")
  }

  test("setHead(10, [1,2,3])") {
    assertEquals(setHead(10, List(1, 2, 3)), List(10, 2, 3))
  }

  test("setHead(10, [1])") {
    assertEquals(setHead(10, List(1)), List(10))
  }

  test("setHead(10, [])") {
    intercept[RuntimeException] {
      val list = setHead(10, Nil)
    }

  }

}
