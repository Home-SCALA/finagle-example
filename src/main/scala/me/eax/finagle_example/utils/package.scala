package me.eax.finagle_example

import scala.util._
import scala.concurrent._

package object utils {
  implicit def scalaToTwitterTry[T](t: Try[T]): com.twitter.util.Try[T] = t match {
    case Success(r) => com.twitter.util.Return(r)
    case Failure(ex) => com.twitter.util.Throw(ex)
  }

  implicit def twitterToScalaTry[T](t: com.twitter.util.Try[T]): Try[T] = t match {
    case com.twitter.util.Return(r) => Success(r)
    case com.twitter.util.Throw(ex) => Failure(ex)
  }

  implicit def scalaToTwitterFuture[T](f: Future[T])(implicit ec: ExecutionContext): com.twitter.util.Future[T] = {
    val promise = com.twitter.util.Promise[T]()
    f.onComplete(promise update _)
    promise
  }

  implicit def twitterToScalaFuture[T](f: com.twitter.util.Future[T]): Future[T] = {
    val promise = Promise[T]()
    f.respond(promise complete _)
    promise.future
  }
}
