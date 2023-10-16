package fpinscala

class EX3_11 extends munit.FunSuite {

  @annotation.tailrec
  final def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = l match
    case Nil     => z
    case x :: xs => foldLeft(xs, f(z, x))(f)

  def my_sum(as: List[Int]): Int = {
    foldLeft(as, 0)(_ + _)
  }

  def my_product(as: List[Int]): Int = {
    foldLeft(as, 1)(_ * _)
  }

  def my_length(as: List[Int]): Int = {
    foldLeft(as, 0)((b, _) => b + 1)
  }

  test("my_sum") {
    assertEquals(my_sum(List(1, 2, 3, 4, 5)), 15)
  }

  test("my_product") {
    assertEquals(my_product(List(1, 2, 3, 4, 5)), 120)
  }

  test("my_length") {
    assertEquals(my_length(List(1, 2, 3, 4, 5)), 5)
  }
}
