package fpinscala

import java.{util => ju}

class EX3_6 extends munit.FunSuite {

  // my initial answer
  def init[A](l: List[A]): List[A] = {
    l match {
      case x :: Nil => Nil
      case x :: xs  => x :: init(xs)
      case Nil      => Nil
    }
  }

  def init2[A](l: List[A]): List[A] = {
    def loop(l: List[A], acc: List[A]): List[A] = {
      l match {
        case x :: Nil => acc
        case x :: xs  => loop(xs, acc :+ x)
        case Nil      => acc
      }
    }
    loop(l, Nil)
  }

  test("init([1,2,3])") {
    System.out.println("Hello")
    System.out.println(init2(List(1, 2, 3)))
  }
}
