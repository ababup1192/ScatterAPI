package controllers

import models.Cat
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import slick.driver.JdbcProfile
import tables.CatTable

import scala.util.Random

class Application extends Controller with CatTable with HasDatabaseConfig[JdbcProfile] {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  import driver.api._

  val Cats = TableQuery[Cats]

  def randomColorCats = Action.async {
    val colors = Seq("White", "Black", "Yellow", "Green", "Red")
    val color = colors(Random.nextInt(colors.size))

    db.run(Cats.filter(_.color === color).result).map { cats =>
      Ok(cats.toString)
    }
  }

  def insert(name: String) = Action.async {
    val colors = Seq("White", "Black", "Yellow", "Green", "Red")
    val color = colors(Random.nextInt(colors.size))

    db.run(Cats += new Cat(name, color)).map { res =>
      Created(res.toString)
    }
  }

}



