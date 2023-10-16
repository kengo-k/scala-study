package fpinscala

class EX3_10 extends munit.FunSuite {

  def my_foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    def loop(list: List[A], acc: B): B = {
      list match {
        case Nil     => acc
        case x :: xs => loop(xs, f(acc, x))
      }
    }
    loop(as, z)
  }

  @annotation.tailrec
  final def foldLeft[A, B](l: List[A], z: B, f: (B, A) => B): B = l match
    case Nil     => z
    case x :: xs => foldLeft(xs, f(z, x), f)

  test("my_foldLeft([1,2,3], 0)((a, b) => a + b)") {
    assertEquals(my_foldLeft(List(1, 2, 3), 0)((a, b) => a + b), 6)
  }

  test("foldLeft([1,2,3], 0)((a, b) => a + b)") {
    assertEquals(foldLeft(List(1, 2, 3), 0, (a, b) => a + b), 6)
  }
}
