package me.eax.finagle_example

import me.eax.finagle_example.services._
import me.eax.finagle_example.utils._

import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import com.twitter.finagle.Service
import com.twitter.finagle.http.Response
import com.twitter.util.{Future => TwitterFuture}
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.util.CharsetUtil

import com.typesafe.scalalogging._
import org.slf4j.LoggerFactory

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class FinagleServiceExample(implicit val bindingModule: BindingModule)
  extends Service[HttpRequest, HttpResponse] with Injectable {

  private val kv = inject[KeyValueStorage]
  private val logger = Logger(LoggerFactory.getLogger(this.getClass))

  logger.info(s"service started")

  def apply(req: HttpRequest): TwitterFuture[HttpResponse] = {
    val resp = Response(req.getProtocolVersion, HttpResponseStatus.OK)
    val key = req.getUri

    req.getMethod match {
      case HttpMethod.GET =>
        logger.debug(s"reading $key")
        for {
          optValue <- kv.get(key)
        } yield {
          optValue match {
            case None =>
              resp.setStatus(HttpResponseStatus.NOT_FOUND)
            case Some(value) =>
              resp.setContentString(value)
          }
          resp
        }
      case HttpMethod.POST =>
        logger.debug(s"writing $key")
        val value = req.getContent.toString(CharsetUtil.UTF_8)
        kv.update(key, value).map(_ => resp)
      case HttpMethod.DELETE =>
        logger.debug(s"deleting $key")
        kv.remove(key).map(_ => resp)
      case _ =>
        logger.error(s"bad request: $req")
        resp.setStatus(HttpResponseStatus.BAD_REQUEST)
        Future.successful(resp)
    }
  }
}
