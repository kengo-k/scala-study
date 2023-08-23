package example

import scala.util.Try

class MonadSpec extends munit.FunSuite {

  //
  // test Monad axioms
  //

  // 1. Left Identity Law: unit(a).flatMap(f) == f(a)

  test("Left Identity Law: Option") {
    val a = Some(1)
    val f = (x: Int) => Some(x * 2)
    assertEquals(a.flatMap(f), f(1))
    assertEquals(a.flatMap(f), Some(2))
    assertEquals(f(1), Some(2))

    val b = None
    assertEquals(b.flatMap(f), None)
  }

  test("Left Identity Law: List") {
    val a = List(1)
    val f = (x: Int) => List(x * 2)
    assertEquals(a.flatMap(f), f(1))
    assertEquals(a.flatMap(f), List(2))
    assertEquals(f(1), List(2))
  }

  test("Left Identity Law: Either") {
    val a = Right(1)
    val f = (x: Int) => Right(x * 2)
    assertEquals(a.flatMap(f), f(1))
    assertEquals(a.flatMap(f), Right(2))
    assertEquals(f(1), Right(2))
  }

  // 2. Right Identity Law: m.flatMap(unit) == m

  test("Right Identity Law: Option") {
    val m = Some(1)
    assertEquals(m.flatMap(Some(_)), m)
    assertEquals(m.flatMap(Some(_)), Some(1))
    assertEquals(m, Some(1))
  }

  test("Right Identity Law: List") {
    val m = List(1, 2, 3)
    assertEquals(m.flatMap(List(_)), m)
    assertEquals(m.flatMap(List(_)), List(1, 2, 3))
    assertEquals(m, List(1, 2, 3))
  }

  // 3. Associativity Law: m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))

  test("Associativity Law: Option") {
    def f(n: Int): Option[String] = Try(n.toString).toOption
    def g(s: String): Option[Double] = Try(s.toDouble).toOption
    def h(d: Double): Option[Boolean] = Some(d >= 0)

    val result1 = Some(3).flatMap(f).flatMap(x => g(x).flatMap(h))
    val result2 = Some(3).flatMap(x => f(x).flatMap(g)).flatMap(h)
    assertEquals(result1, result2)
    assertEquals(result1, Some(true))
    assertEquals(result2, Some(true))

    val result3 = Some(-1).flatMap(x => f(x).flatMap(g)).flatMap(h)
    assertEquals(result3, Some(false))
  }

  // test flatMap

  test("Option.flatMap") {
    val userIdMap =
      Map(
        "bob" -> "Bob.Smith",
        "alice" -> "Alice.Johnson"
      )
    val passwordMap =
      Map("Bob.Smith" -> "1234", "Alice.Johnson" -> "2222")
    def getUserId(userName: String): Option[String] = {
      userIdMap.get(userName)
    }
    def login(userName: String, password: String): Option[Boolean] = {
      val userId: Option[String] = userIdMap.get(userName)
      userId.flatMap(id => passwordMap.get(id).map(_ == password))
    }
    assertEquals(login("bob", "1234"), Some(true))
    assertEquals(login("bob", "2222"), Some(false))
    assertEquals(login("alice", "2222"), Some(true))
    assertEquals(login("john", "9999"), None)
  }

  test("Either.flatMap") {
    case class User(name: String, mail: Option[String])
    val userIdMap =
      Map(
        "bob" -> "Bob.Smith",
        "alice" -> "Alice.Johnson"
      )
    val passwordMap =
      Map(
        ("Bob.Smith", "1234") -> User("Bob.Smith", Some("bob@example.com")),
        ("Alice.Johnson", "2222") -> User("Alice.Johnson", None)
      )
    def getUserId(userName: String): Either[String, String] = {
      userIdMap.get(userName).toRight("User not found")

      // The above process is equivalent to the following process.
      //
      // userIdMap.get(userName) match {
      //   case Some(id) => Right(id)
      //   case None     => Left("User not found")
      // }
    }
    def getUserEmail(
        userName: String,
        password: String
    ): Either[String, Option[String]] = {
      val userId: Either[String, String] = getUserId(userName)
      userId.flatMap(id => {
        val user = passwordMap.get((id, password)).toRight("Password not match")
        user.map(_.mail)
      })

      // The above process can be rewritten using a `for` comprehension.
      //
      // for {
      //   userId <- getUserId(userName)
      //   user <- passwordMap.get((userId, password)).toRight("Password not match")
      // } yield user.mail
    }

    assertEquals(getUserEmail("bob", "1234"), Right(Some("bob@example.com")))
    assertEquals(getUserEmail("bob", "9999"), Left("Password not match"))
    assertEquals(getUserEmail("alice", "2222"), Right(None))
    assertEquals(getUserEmail("alice", "9999"), Left("Password not match"))
    assertEquals(getUserEmail("john", "9999"), Left("User not found"))
  }

  test("List.flatMap") {
    val list = List(List(1), List(2, 3, 4), List(), List(5))
    val f = (l: List[Int]) => l
    assertEquals(list.flatMap(f), List(1, 2, 3, 4, 5))
  }

}
