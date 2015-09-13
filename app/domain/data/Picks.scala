package domain.data

import domain._
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.MSSQLServer
import org.squeryl.annotations.Column
import org.squeryl.{Schema, Session}
import play.api.Play

class PickSchema extends Schema {
  def picks = table[PickShims]("Picks")
}

case class PickShims(@Column("User") user: String,
                     @Column("Week") week: Int,
                     @Column("Game") game: String,
                     @Column("Pick") pick: String,
                     @Column("Confidence") confidence: Int)

trait PickRepository {
  def loadPicks(): Map[String, Map[Week, Seq[Pick]]]
  def loadPicks(user: User, week: Week): Seq[Pick]
  def loadPicks(username: String, week: Week): Seq[Pick]
  def loadPick(user: User, week: Week, game: Game): Pick
  def savePick(user: User, pick: Pick): Unit
}

class Repository extends PickRepository {
  lazy val databaseConnectionUrl = Play.current.configuration.getString("db.url").get
  lazy val databaseUsername = Play.current.configuration.getString("db.username").get
  lazy val databasePassword = Play.current.configuration.getString("db.password").get
  Class.forName("net.sourceforge.jtds.jdbc.Driver")

  def loadPicks(): Map[String, Map[Week, Seq[Pick]]] = {
    val session = Session.create(java.sql.DriverManager.getConnection(databaseConnectionUrl, databaseUsername, databasePassword), new MSSQLServer)
    using(session) {
      val picks = new PickSchema().picks.toList
      val picksByUser = picks.groupBy(x => x.user)

      val rawPackage = picksByUser.map(x => (x._1, x._2.groupBy(y => y.week)))
      val results = rawPackage.map(x => (x._1, x._2.map(y => (Week.getWeek(y._1), y._2.map(p => new Pick(Game.getGame(Week.getWeek(p.week), p.game), Team.allTeams.find(x => x.abbreviation == p.pick).get, Confidence(p.confidence)))))))
      results
    }
  }

  def loadPicks(user: User, week: Week): Seq[Pick] = {
    loadPicks(user.username, week)
  }

  def loadPicks(username: String, week: Week): Seq[Pick] = {
    val session = Session.create(java.sql.DriverManager.getConnection(databaseConnectionUrl, databaseUsername, databasePassword), new MSSQLServer)
    using(session) {
      val picks = new PickSchema().picks.toList.filter(x => x.user == username && x.week == week.number)
      picks.map(p => new Pick(Game.getGame(Week.getWeek(p.week), p.game), Team.allTeams.find(x => x.abbreviation == p.pick).get, Confidence(p.confidence)))
    }
  }

  def loadPick(user: User, week: Week, game: Game): Pick = {
    val session = Session.create(java.sql.DriverManager.getConnection(databaseConnectionUrl, databaseUsername, databasePassword), new MSSQLServer)
    using(session) {
      val pick = new PickSchema().picks.toList.find(x => x.user == user.username && x.week == week.number && x.game == game.shortString).get
      new Pick(Game.getGame(Week.getWeek(pick.week), pick.game), Team.allTeams.find(x => x.abbreviation == pick.pick).get, Confidence(pick.confidence))
    }
  }

  def savePick(user: User, pick: Pick): Unit = {
    val session = Session.create(java.sql.DriverManager.getConnection(databaseConnectionUrl, databaseUsername, databasePassword), new MSSQLServer)
    using(session) {
      val schema = new PickSchema()
      val week = Game.getWeek(pick.game)

      update(schema.picks)(s =>
        where(s.user === user.username and s.week === week.number and s.game === pick.game.shortString)
          set(s.pick := pick.selectedTeam.abbreviation,
          s.confidence := pick.confidence.rating)
      )
    }
  }
}