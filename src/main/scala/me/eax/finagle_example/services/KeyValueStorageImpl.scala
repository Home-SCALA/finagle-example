package me.eax.finagle_example.services

import scala.collection.concurrent.TrieMap
import scala.concurrent._

class KeyValueStorageImpl extends KeyValueStorage {
  private val kv = TrieMap.empty[String, String]

  def get(key: String): Future[Option[String]] = {
    val result = kv.get(key)
    Future.successful(result)
  }

  def update(key: String, value: String): Future[Unit] = {
    val result = kv.update(key, value)
    Future.successful(result)
  }

  def remove(key: String): Future[Unit] = {
    kv.remove(key)
    Future.successful({})
  }
}
