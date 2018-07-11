package org.talend.training

case class LogWriter[A](value: A, log: String) {
  def flatMap[B](f: A => LogWriter[B]): LogWriter[B] = {
    val result = f(value)
    LogWriter(result.value, log + "\n" + result.log)
  }
  def map[B](f: A => B) = LogWriter(f(value), log)
}

object LogWithWriterPattern {

  def foo(i: Int): LogWriter[Double] = LogWriter(i, "compute double by foo")

  def bar(d: Double): LogWriter[String] =
    LogWriter(d.toString, "stringify by bar")

  def baz(s: String): LogWriter[String] =
    LogWriter(s"$s from baz", "decorated by baz")

  //def action(i: Int): LogWriter[String] = foo(i).flatMap(bar).flatMap(baz)

  // def action2(i: Int): LogWriter[String] = foo(i).flatMap(a => bar(a))

  def action(i: Int): LogWriter[String] = for {
    a <- foo(i)
    b <- bar(a)
    c <- baz(b)
  } yield c

}
