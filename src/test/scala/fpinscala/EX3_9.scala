package fpinscala

class EX3_9 extends munit.FunSuite {

  // my initial answer
  def my_length[A](as: List[A]): Int = {
    as.foldRight(0) { (_, acc) => acc + 1 }
  }

  test("my_length([1,2,3])") {
    assertEquals(my_length(List(1, 2, 3)), 3)
  }
}
