package fpinscala

import java.{util => ju}

class EX3_4 extends munit.FunSuite {

  // my initial answer
  def drop[A](l: List[A], n: Int): List[A] =
    if (n > l.length) {
      sys.error("length over")
    }
    n match {
      case 0 => l
      case x => drop(l.tail, n - 1)
    }

  // answer
  def dropAnswer[A](l: List[A], n: Int): List[A] =
    if (n <= 0) then l
    else
      l match
        case Nil    => Nil
        case _ :: t => dropAnswer(t, n - 1)

  test("drop([1,2,3], 1)") {
    assertEquals(drop(List(1, 2, 3), 1), List(2, 3))
    assertEquals(dropAnswer(List(1, 2, 3), 1), List(2, 3))
  }

  test("drop([1,2,3], 2)") {
    assertEquals(drop(List(1, 2, 3), 2), List(3))
    assertEquals(dropAnswer(List(1, 2, 3), 2), List(3))
  }

  test("drop([1,2,3], 3)") {
    assertEquals(drop(List(1, 2, 3), 3), List())
    assertEquals(dropAnswer(List(1, 2, 3), 3), List())
  }

  test("drop([1,2,3], 4)") {
    intercept[RuntimeException] {
      val l = drop(List(1, 2, 3), 4)
    }
    assertEquals(dropAnswer(List(1, 2, 3), 4), List())
  }

}
