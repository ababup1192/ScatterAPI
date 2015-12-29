package tables

import models.Cat
import slick.driver.JdbcProfile

trait CatTable {
  protected val driver: JdbcProfile

  import driver.api._

  class Cats(tag: Tag) extends Table[Cat](tag, "cat") {

    def name = column[String]("NAME", O.PrimaryKey)

    def color = column[String]("COLOR")

    def * = (name, color) <>(Cat.tupled, Cat.unapply)
  }

}