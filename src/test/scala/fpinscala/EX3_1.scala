package fpinscala

import java.{util => ju}

class EX3_2 extends munit.FunSuite {
  // my initial answer
  def tail[A](l: List[A]): List[A] = {
    l match {
      case x :: xs => xs
      case Nil     => throw new ju.NoSuchElementException
    }
  }

  // answer
  // Cons has been removed, so this code cannot be built.
  // def tailAnswer[A](l: List[A]): List[A] =
  //   l match
  //     case Nil        => sys.error("tail of empty list")
  //     case Cons(_, t) => t

  test("tail([1,2])") {
    assertEquals(tail(List(1, 2)), List(2))
  }

  test("tail([1])") {
    assertEquals(tail(List(1)), Nil)
  }

  test("tail(Nil)") {
    intercept[ju.NoSuchElementException] {
      val t = tail(List())
    }
  }
}
