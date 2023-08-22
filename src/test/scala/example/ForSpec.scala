package example

class ForSpec extends munit.FunSuite {

  test("for") {
    var sum = 0
    for (i <- 1 to 5) {
      sum += i
    }
    assertEquals(sum, 15)
  }

  test("for list") {
    val values = List(1, 2, 3, 4, 5)
    var sum = 0
    for (i <- values) {
      sum += i
    }
    assertEquals(sum, 15)
  }

  test("for zip") {
    var list = List[(Int, String)]()
    val chars = List('a', 'b')
    for (i <- 1 to 3; c <- chars) {
      list = list :+ (i, c.toString)
    }
    assertEquals(
      list,
      List((1, "a"), (1, "b"), (2, "a"), (2, "b"), (3, "a"), (3, "b"))
    )
  }

  test("for guard") {
    val values = List(1, 2, 3, 4, 5)
    var sum = 0
    for (i <- values if i % 2 == 0) {
      sum += i
    }
    assertEquals(sum, 6)
  }

  test("for bind") {
    var sum = 0;
    for (i <- 1 to 3; doubled = i * 2) {
      sum += doubled
    }
    assertEquals(sum, 12)
  }

  test("for yield") {
    val values = for (i <- 1 to 3) yield i * 2
    assertEquals(values, Vector(2, 4, 6))
  }

  test("for option1") {
    val a = Some(1)
    val b = Some(2)
    val sum = for {
      x <- a
      y <- b
    } yield x + y
    assertEquals(sum, Some(3))
  }

  test("for option2") {
    val a = Some(1)
    val b: Option[Int] = None
    val sum = for {
      x <- a
      y <- b
    } yield x + y
    assertEquals(sum, None)
  }

  test("for option3") {
    val list = List(Some(1), None, Some(2))
    val sum = for {
      x <- list
      y <- x
    } yield y
    assertEquals(sum, List(1, 2))
  }

  test("for either1") {
    val e1: Either[String, Int] = Right(1)
    val e2: Either[String, Int] = Left("error1")
    val e3: Either[String, Int] = Left("error2")
    val result = for {
      x <- e1
      y <- e2
      z <- e3
    } yield x + y + z
    assertEquals(result, Left("error1"))
  }

  test("for either2") {
    val e1: Either[String, Int] = Right(1)
    val e2: Either[String, Int] = Right(2)
    val e3: Either[String, Int] = Right(3)
    val result = for {
      x <- e1
      y <- e2
      z <- e3
    } yield x + y + z
    assertEquals(result, Right(6))
  }
}
