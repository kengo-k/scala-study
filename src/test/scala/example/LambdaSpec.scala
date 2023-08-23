package example

class LambdaSpec extends munit.FunSuite {

  test("List.map") {
    val list = List(1, 2, 3, 4)
    assertEquals(list.map(x => x * 2), List(2, 4, 6, 8))
    assertEquals(list.map(_ * 2), List(2, 4, 6, 8))
  }

  test("List.sortWith") {
    val list = List(3, 1, 5, 9)
    assertEquals(list.sortWith((x, y) => x < y), List(1, 3, 5, 9))
    assertEquals(list.sortWith(_ < _), List(1, 3, 5, 9))
  }

}
