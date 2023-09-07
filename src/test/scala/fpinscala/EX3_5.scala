package fpinscala

import java.{util => ju}

class EX3_5 extends munit.FunSuite {

  // my initial answer
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match
    case x :: xs if f(x)  => dropWhile(xs, f)
    case x :: xs if !f(x) => x :: xs
    case Nil              => Nil

  // answer
  def dropWhileAnswer[A](l: List[A], f: A => Boolean): List[A] =
    l match
      case x :: xs if f(x) => dropWhile(xs, f)
      case _               => l

  def id[A](a: A): A => A = a => a

  test("dropWhile([true, true, false])") {
    assertEquals(dropWhile(List(true, true, false), id(true)), List(false))
  }

  test("dropWhile([true, false, false])") {
    assertEquals(
      dropWhile(List(true, false, false), id(true)),
      List(false, false)
    )
  }

  test("dropWhile([false, true, false])") {
    assertEquals(
      dropWhile(List(false, true, false), id(true)),
      List(false, true, false)
    )
  }

}
