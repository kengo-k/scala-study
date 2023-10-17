package fpinscala

class EX3_12 extends munit.FunSuite {

  def my_reverse(as: List[Int]): List[Int] = {
    as.foldLeft[List[Int]](Nil) { (acc, value) => value :: acc }
  }

  test("my_reverse(1,2,3)") {
    assertEquals(my_reverse(List(1, 2, 3)), List(3, 2, 1))
  }

}
