package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

/**
 * Created by turgay on 11/12/13.
 */

/**
 * Helper for pagination.
 */
case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

case class Word(id: Pk[Long] = NotAssigned, name: String)


object Word {
    
  val simple = {
    get[Pk[Long]]("id") ~
    get[String]("name") map {
      case id~name => Word(id, name)
    }
  }
  
  /**
   * Construct the Map[String,String] needed to fill a select options set.
   */
  def options: Seq[(String,String)] = DB.withConnection { implicit connection =>
    SQL("select * from Word order by name").as(Word.simple *).map(c => c.id.toString -> c.name)
  }
  
  def list(page: Int = 0, pageSize: Int = 40, orderBy: Int = 2, word: String = ""): Page[Word] = {
    
    val offset = pageSize * page
    val filter = word.replaceAll("\\*","%")
    
    DB.withConnection { implicit connection =>
      
      val wordList = SQL(
        """
          select id,name from Word 
          where name like {filter}
          order by {orderBy}
          limit {offset}, {pageSize}
        """
      ).on(
        'pageSize -> pageSize, 
        'offset -> offset,
        'filter -> filter,
        'orderBy -> orderBy
      ).as(Word.simple *)

      val totalRows = SQL(
        """
          select count(*) from word 
          where word.name like {filter}
        """
      ).on(
        'filter -> filter
      ).as(scalar[Long].single)

      Page(wordList, page, offset, totalRows)
      
    }
  }
}

