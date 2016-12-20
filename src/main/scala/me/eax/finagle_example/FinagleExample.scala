package me.eax.finagle_example

import com.escalatesoft.subcut.inject.NewBindingModule._
import com.twitter.finagle._
import com.twitter.finagle.http.{Http => _}
import com.twitter.util._
import me.eax.finagle_example.services._

object FinagleExample extends App {
  implicit val bindings = newBindingModule { module =>
    import module._

    bind[KeyValueStorage] toSingle new KeyValueStorageImpl
  }

  val service = new FinagleServiceExample
  val server = Http.serve(":8080", service)
  Await.ready(server)
}
