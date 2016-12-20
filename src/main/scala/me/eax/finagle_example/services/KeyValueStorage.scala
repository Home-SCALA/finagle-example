package me.eax.finagle_example.services

import scala.concurrent._

trait KeyValueStorage {
  def get(key: String): Future[Option[String]]
  def update(key: String, value: String): Future[Unit]
  def remove(key: String): Future[Unit]
}
