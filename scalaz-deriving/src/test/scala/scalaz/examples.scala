// Copyright: 2017 Sam Halliday
// License: http://www.gnu.org/licenses/lgpl-3.0.en.html

package examples

import java.lang.String
import scala.{ Boolean, Int }

import scalaz._
import Scalaz._

package adt {
  sealed trait Foo
  final case class Bar(s: String)          extends Foo
  final case class Faz(b: Boolean, i: Int) extends Foo
  final case object Baz extends Foo {
    implicit val equal: Equal[Baz.type]     = Derivez.gen[Equal, Baz.type]
    implicit val default: Default[Baz.type] = Derivez.gen[Default, Baz.type]
  }
  object Bar {
    implicit val equal: Equal[Bar]     = Derivez.gen[Equal, Bar]
    implicit val default: Default[Bar] = Derivez.gen[Default, Bar]
  }
  object Faz {
    implicit val equal: Equal[Faz]     = Derivez.gen[Equal, Faz]
    implicit val default: Default[Faz] = Derivez.gen[Default, Faz]
  }
  object Foo {
    implicit val equal: Equal[Foo]     = Derivez.gen[Equal, Foo]
    implicit val default: Default[Foo] = Derivez.gen[Default, Foo]
  }
}

// more complex recursive type example
package recadt {
  sealed trait ATree
  final case class Leaf(value: String)               extends ATree
  final case class Branch(left: ATree, right: ATree) extends ATree

  object Leaf {
    implicit val equal: Equal[Leaf]     = Derivez.gen[Equal, Leaf]
    implicit val default: Default[Leaf] = Derivez.gen[Default, Leaf]
  }
  object Branch {
    implicit val equal: Equal[Branch]     = Derivez.gen[Equal, Branch]
    implicit val default: Default[Branch] = Derivez.gen[Default, Branch]
  }
  object ATree {
    implicit val equal: Equal[ATree]     = Derivez.gen[Equal, ATree]
    implicit val default: Default[ATree] = Derivez.gen[Default, ATree]
  }
}

// more complex recursive GADT type example
package recgadt {
  sealed trait GTree[A]
  final case class GLeaf[A](value: A)                          extends GTree[A]
  final case class GBranch[A](left: GTree[A], right: GTree[A]) extends GTree[A]

  object GLeaf {
    implicit def equal[A: Equal]: Equal[GLeaf[A]] = Derivez.gen[Equal, GLeaf[A]]
    implicit def default[A: Default]: Default[GLeaf[A]] =
      Derivez.gen[Default, GLeaf[A]]
  }
  object GBranch {
    implicit def equal[A: Equal]: Equal[GBranch[A]] =
      Derivez.gen[Equal, GBranch[A]]
    implicit def default[A: Default]: Default[GBranch[A]] =
      Derivez.gen[Default, GBranch[A]]
  }
  object GTree {
    implicit def equal[A: Equal]: Equal[GTree[A]] = Derivez.gen[Equal, GTree[A]]
    implicit def default[A: Default]: Default[GTree[A]] =
      Derivez.gen[Default, GTree[A]]
  }
}
