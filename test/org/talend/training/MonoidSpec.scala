package org.talend.training

import org.specs2.mutable.Specification

trait Monoid[T] {
  def neutral: T
  def binop(a: T, b: T): T
}

object MonoidSpec {

  object IntAddMonoid extends Monoid[Int] {
    override def neutral: Int          = 0
    override def binop(a: Int, b: Int) = a + b
  }

  object IntMulMonoid extends Monoid[Int] {
    override def neutral: Int          = 1
    override def binop(a: Int, b: Int) = a * b
  }

  def foldWithMonoid[T](l: List[T])(M: Monoid[T]): T =
    l.foldLeft(M.neutral)((acc, current) => M.binop(acc, current))

  def sum(list: List[Int]) = foldWithMonoid(list)(IntAddMonoid)

}

class MonoidSpec extends Specification {

  import MonoidSpec._

  "sum" should {
    "return the sum of list elements" in {

      val result = sum(List(1, 2, 3, 4))

      result === 10
    }
  }

}
