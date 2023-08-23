package example

import scala.concurrent.Await
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.math.BigDecimal

class FutureSpec extends munit.FunSuite {

  test("sum 1..100000000") {
    val n = 10000000
    val batchSize = 5000000
    val numBatches = n / batchSize

    val ranges = (1 to n by batchSize).map(i => i to (i + batchSize - 1))

    val futureSums = ranges.map { range =>
      Future {
        val start = System.currentTimeMillis()
        val result = range.foldLeft(BigDecimal(0))(_ + _)
        val end = System.currentTimeMillis()
        println(
          s"End Thread@${Thread.currentThread().getName}:  ${end - start}ms"
        )
        result
      }
    }

    val totalSumFuture: Future[BigDecimal] =
      Future.sequence(futureSums).map(_.sum)

    totalSumFuture.onComplete {
      case scala.util.Success(value) => println(s"Total sum: $value")
      case scala.util.Failure(e) =>
        println(s"An error occurred: ${e.getMessage}")
    }

    val result: BigDecimal = Await.result(totalSumFuture, 1 minute)

    val expected = BigDecimal(n) * (n + 1) / 2
    assertEquals(result, expected)
  }
}
