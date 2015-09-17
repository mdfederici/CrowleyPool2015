package domain

import java.time.LocalDateTime

class Game(visitor: Team,
           host: Team,
           startTime: LocalDateTime,
           victor: Option[Team] = None,
           visitorScore: Option[Int] = None,
           hostScore: Option[Int] = None) {

  private var _winner: Option[Team] = victor
  private var _homeScore: Option[Int] = hostScore
  private var _awayScore: Option[Int] = visitorScore

  def awayTeam = visitor
  def homeTeam = host
  def kickoff = startTime
  def winner = _winner
  def awayScore = _awayScore
  def homeScore = _homeScore

  def winner_=(team: Team): Unit = {
    _winner = Some(team)
  }

  def awayScore_=(score: Int): Unit = {
    _awayScore = Some(score)
  }

  def homeScore_=(score: Int): Unit = {
    _homeScore = Some(score)
  }

  def isTeamParticipating(team: Team): Boolean = {
    team == homeTeam || team == awayTeam
  }

  override def toString: String = {
    s"$awayTeam at $homeTeam at ${kickoff.format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a"))}"
  }

  def shortStringWithScores: String = {
    val away =
      awayScore match {
        case Some(s) => s"($s)"
        case None => ""
      }

    val home =
      homeScore match {
        case Some(s) => s"($s)"
        case None => ""
      }

    s"${awayTeam.abbreviation}$away at ${homeTeam.abbreviation}$home"
  }

  def shortString: String = {
    s"${awayTeam.abbreviation} at ${homeTeam.abbreviation}"
  }
}

object Game {
  def getGames(week: Week): Seq[Game] = {
    week match {
      case Week.Week1 => week1Games
      case Week.Week2 => week2Games
      case Week.Week3 => week3Games
      case Week.Week4 => week4Games
      case Week.Week5 => week5Games
      case Week.Week6 => week6Games
      case Week.Week7 => week7Games
      case Week.Week8 => week8Games
      case Week.Week9 => week9Games
      case Week.Week10 => week10Games
      case Week.Week11 => week11Games
      case Week.Week12 => week12Games
      case Week.Week13 => week13Games
      case Week.Week14 => week14Games
      case Week.Week15 => week15Games
      case Week.Week16 => week16Games
      case Week.Week17 => week17Games
    }
  }

  def getGame(week: Week, gameShortString: String): Game = {
    val games = getGames(week)
    val game = games.find(x => x.shortString == gameShortString)
    game.getOrElse(throw new Exception(s"There is no game '$gameShortString' in week $week."))
  }

  def getWeek(game: Game): Week = {
    if(week1Games.contains(game)) { return Week.Week1 }
    if(week2Games.contains(game)) { return Week.Week2 }
    if(week3Games.contains(game)) { return Week.Week3 }
    if(week4Games.contains(game)) { return Week.Week4 }
    if(week5Games.contains(game)) { return Week.Week5 }
    if(week6Games.contains(game)) { return Week.Week6 }
    if(week7Games.contains(game)) { return Week.Week7 }
    if(week8Games.contains(game)) { return Week.Week8 }
    if(week9Games.contains(game)) { return Week.Week9 }
    if(week10Games.contains(game)) { return Week.Week10 }
    if(week11Games.contains(game)) { return Week.Week11 }
    if(week12Games.contains(game)) { return Week.Week12 }
    if(week13Games.contains(game)) { return Week.Week13 }
    if(week14Games.contains(game)) { return Week.Week14 }
    if(week15Games.contains(game)) { return Week.Week15 }
    if(week16Games.contains(game)) { return Week.Week16 }
    if(week17Games.contains(game)) { return Week.Week17 }

    throw new Exception(s"Could not find a week for $game.")
  }

  val week1Games = Seq(
    //Thursday Night game
    new Game(Team.Steelers, Team.Patriots, LocalDateTime.of(2015, 9, 10, 20, 30), Some(Team.Patriots), Some(21), Some(28)),

    //1pm games
    new Game(Team.Packers, Team.Bears, LocalDateTime.of(2015, 9, 13, 13, 0), Some(Team.Packers), Some(31), Some(23)),
    new Game(Team.Chiefs, Team.Texans, LocalDateTime.of(2015, 9, 13, 13, 0), Some(Team.Chiefs), Some(27), Some(20)),
    new Game(Team.Browns, Team.Jets, LocalDateTime.of(2015, 9, 13, 13, 0), Some(Team.Jets), Some(10), Some(31)),
    new Game(Team.Colts, Team.Bills, LocalDateTime.of(2015, 9, 13, 13, 0), Some(Team.Bills), Some(14), Some(27)),
    new Game(Team.Dolphins, Team.Redskins, LocalDateTime.of(2015, 9, 13, 13, 0), Some(Team.Dolphins), Some(17), Some(10)),
    new Game(Team.Panthers, Team.Jaguars, LocalDateTime.of(2015, 9, 13, 13, 0), Some(Team.Panthers), Some(20), Some(9)),
    new Game(Team.Seahawks, Team.Rams, LocalDateTime.of(2015, 9, 13, 13, 0), Some(Team.Rams), Some(31), Some(34)),

    //4pm games
    new Game(Team.Saints, Team.Cardinals, LocalDateTime.of(2015, 9, 13, 16, 5), Some(Team.Cardinals), Some(19), Some(31)),
    new Game(Team.Lions, Team.Chargers, LocalDateTime.of(2015, 9, 13, 16, 5), Some(Team.Chargers), Some(28), Some(33)),
    new Game(Team.Titans, Team.Buccaneers, LocalDateTime.of(2015, 9, 13, 16, 25), Some(Team.Titans), Some(42), Some(14)),
    new Game(Team.Bengals, Team.Raiders, LocalDateTime.of(2015, 9, 13, 16, 25), Some(Team.Bengals), Some(33), Some(13)),
    new Game(Team.Ravens, Team.Broncos, LocalDateTime.of(2015, 9, 13, 16, 25), Some(Team.Broncos), Some(13), Some(19)),

    //Sunday Night game
    new Game(Team.Giants, Team.Cowboys, LocalDateTime.of(2015, 9, 13, 20, 30), Some(Team.Cowboys), Some(26), Some(27)),

    //Monday Night game
    new Game(Team.Eagles, Team.Falcons, LocalDateTime.of(2015, 9, 14, 19, 10), Some(Team.Falcons), Some(24), Some(26)),
    new Game(Team.Vikings, Team.FourtyNiners, LocalDateTime.of(2015, 9, 14, 22, 20), Some(Team.FourtyNiners), Some(3), Some(20))
  )

  val week2Games = Seq(
    //Thursday Night game
    new Game(Team.Broncos, Team.Chiefs, LocalDateTime.of(2015, 9, 17, 20, 25)),

    //1pm games
    new Game(Team.Texans, Team.Panthers, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.FourtyNiners, Team.Steelers, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.Buccaneers, Team.Saints, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.Lions, Team.Vikings, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.Cardinals, Team.Bears, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.Patriots, Team.Bills, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.Chargers, Team.Bengals, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.Titans, Team.Browns, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.Falcons, Team.Giants, LocalDateTime.of(2015, 9, 20, 13, 0)),
    new Game(Team.Rams, Team.Redskins, LocalDateTime.of(2015, 9, 20, 13, 0)),

    //4pm games
    new Game(Team.Dolphins, Team.Jaguars, LocalDateTime.of(2015, 9, 20, 16, 5)),
    new Game(Team.Ravens, Team.Raiders, LocalDateTime.of(2015, 9, 20, 16, 5)),
    new Game(Team.Cowboys, Team.Eagles, LocalDateTime.of(2015, 9, 20, 16, 25)),

    //Sunday Night game
    new Game(Team.Seahawks, Team.Packers, LocalDateTime.of(2015, 9, 20, 20, 30)),

    //Monday Night game
    new Game(Team.Jets, Team.Colts, LocalDateTime.of(2015, 9, 21, 22, 30))
  )

  val week3Games = Seq(
    //Thursday Night game
    new Game(Team.Redskins, Team.Giants, LocalDateTime.of(2015, 9, 24, 20, 25)),

    //1pm games
    new Game(Team.Falcons, Team.Cowboys, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Colts, Team.Titans, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Raiders, Team.Browns, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Bengals, Team.Ravens, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Jaguars, Team.Patriots, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Saints, Team.Panthers, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Eagles, Team.Jets, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Buccaneers, Team.Texans, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Chargers, Team.Vikings, LocalDateTime.of(2015, 9, 27, 13, 0)),
    new Game(Team.Steelers, Team.Rams, LocalDateTime.of(2015, 9, 27, 13, 0)),

    //4pm games
    new Game(Team.FourtyNiners, Team.Cardinals, LocalDateTime.of(2015, 9, 27, 16, 5)),
    new Game(Team.Bills, Team.Dolphins, LocalDateTime.of(2015, 9, 27, 16, 25)),
    new Game(Team.Bears, Team.Seahawks, LocalDateTime.of(2015, 9, 27, 16, 25)),

    //Sunday Night game
    new Game(Team.Broncos, Team.Lions, LocalDateTime.of(2015, 9, 27, 20, 30)),

    //Monday Night game
    new Game(Team.Chiefs, Team.Packers, LocalDateTime.of(2015, 9, 28, 22, 30))
  )

  val week4Games = Seq(
    //Thursday Night game
    new Game(Team.Ravens, Team.Steelers, LocalDateTime.of(2015, 10, 1, 20, 25)),

    //1pm games
    new Game(Team.Jets, Team.Dolphins, LocalDateTime.of(2015, 10, 4, 13, 0)),
    new Game(Team.Jaguars, Team.Colts, LocalDateTime.of(2015, 10, 4, 13, 0)),
    new Game(Team.Giants, Team.Bills, LocalDateTime.of(2015, 10, 4, 13, 0)),
    new Game(Team.Panthers, Team.Buccaneers, LocalDateTime.of(2015, 10, 4, 13, 0)),
    new Game(Team.Eagles, Team.Redskins, LocalDateTime.of(2015, 10, 4, 13, 0)),
    new Game(Team.Raiders, Team.Bears, LocalDateTime.of(2015, 10, 4, 13, 0)),
    new Game(Team.Texans, Team.Falcons, LocalDateTime.of(2015, 10, 4, 13, 0)),
    new Game(Team.Chiefs, Team.Bengals, LocalDateTime.of(2015, 10, 4, 13, 0)),

    //4pm games
    new Game(Team.Browns, Team.Chargers, LocalDateTime.of(2015, 10, 4, 16, 5)),
    new Game(Team.Packers, Team.FourtyNiners, LocalDateTime.of(2015, 10, 4, 16, 25)),
    new Game(Team.Rams, Team.Cardinals, LocalDateTime.of(2015, 10, 4, 16, 25)),
    new Game(Team.Vikings, Team.Broncos, LocalDateTime.of(2015, 10, 4, 16, 25)),

    //Sunday Night game
    new Game(Team.Cowboys, Team.Saints, LocalDateTime.of(2015, 10, 4, 20, 30)),

    //Monday Night game
    new Game(Team.Lions, Team.Seahawks, LocalDateTime.of(2015, 10, 5, 22, 30))
  )

  val week5Games = Seq(
    //Thursday Night game
    new Game(Team.Colts, Team.Texans, LocalDateTime.of(2015, 10, 8, 20, 25)),

    //1pm games
    new Game(Team.Bears, Team.Chiefs, LocalDateTime.of(2015, 10, 11, 13, 0)),
    new Game(Team.Seahawks, Team.Bengals, LocalDateTime.of(2015, 10, 11, 13, 0)),
    new Game(Team.Redskins, Team.Falcons, LocalDateTime.of(2015, 10, 11, 13, 0)),
    new Game(Team.Jaguars, Team.Buccaneers, LocalDateTime.of(2015, 10, 11, 13, 0)),
    new Game(Team.Saints, Team.Eagles, LocalDateTime.of(2015, 10, 11, 13, 0)),
    new Game(Team.Browns, Team.Ravens, LocalDateTime.of(2015, 10, 11, 13, 0)),
    new Game(Team.Rams, Team.Packers, LocalDateTime.of(2015, 10, 11, 13, 0)),
    new Game(Team.Bills, Team.Titans, LocalDateTime.of(2015, 10, 11, 13, 0)),

    //4pm games
    new Game(Team.Cardinals, Team.Lions, LocalDateTime.of(2015, 10, 11, 16, 5)),
    new Game(Team.Patriots, Team.Cowboys, LocalDateTime.of(2015, 10, 11, 16, 25)),
    new Game(Team.Broncos, Team.Raiders, LocalDateTime.of(2015, 10, 11, 16, 25)),

    //Sunday Night game
    new Game(Team.FourtyNiners, Team.Giants, LocalDateTime.of(2015, 10, 11, 20, 30)),

    //Monday Night game
    new Game(Team.Steelers, Team.Chargers, LocalDateTime.of(2015, 10, 12, 22, 30))
  )

  val week6Games = Seq(
    //Thursday Night game
    new Game(Team.Falcons, Team.Saints, LocalDateTime.of(2015, 10, 15, 20, 25)),

    //1pm games
    new Game(Team.Redskins, Team.Jets, LocalDateTime.of(2015, 10, 18, 13, 0)),
    new Game(Team.Cardinals, Team.Steelers, LocalDateTime.of(2015, 10, 18, 13, 0)),
    new Game(Team.Chiefs, Team.Vikings, LocalDateTime.of(2015, 10, 18, 13, 0)),
    new Game(Team.Bengals, Team.Bills, LocalDateTime.of(2015, 10, 18, 13, 0)),
    new Game(Team.Bears, Team.Lions, LocalDateTime.of(2015, 10, 18, 13, 0)),
    new Game(Team.Broncos, Team.Browns, LocalDateTime.of(2015, 10, 18, 13, 0)),
    new Game(Team.Texans, Team.Jaguars, LocalDateTime.of(2015, 10, 18, 13, 0)),
    new Game(Team.Dolphins, Team.Titans, LocalDateTime.of(2015, 10, 18, 13, 0)),

    //4pm games
    new Game(Team.Panthers, Team.Seahawks, LocalDateTime.of(2015, 10, 18, 16, 5)),
    new Game(Team.Chargers, Team.Packers, LocalDateTime.of(2015, 10, 18, 16, 25)),
    new Game(Team.Ravens, Team.FourtyNiners, LocalDateTime.of(2015, 10, 18, 16, 25)),

    //Sunday Night game
    new Game(Team.Patriots, Team.Colts, LocalDateTime.of(2015, 10, 18, 20, 30)),

    //Monday Night game
    new Game(Team.Giants, Team.Eagles, LocalDateTime.of(2015, 10, 19, 22, 30))
  )

  val week7Games = Seq(
    //Thursday Night game
    new Game(Team.Seahawks, Team.FourtyNiners, LocalDateTime.of(2015, 10, 22, 20, 25)),

    //1pm games
    new Game(Team.Bills, Team.Jaguars, LocalDateTime.of(2015, 10, 25, 9, 30)),
    new Game(Team.Buccaneers, Team.Redskins, LocalDateTime.of(2015, 10, 25, 13, 0)),
    new Game(Team.Falcons, Team.Titans, LocalDateTime.of(2015, 10, 25, 13, 0)),
    new Game(Team.Saints, Team.Colts, LocalDateTime.of(2015, 10, 25, 13, 0)),
    new Game(Team.Vikings, Team.Lions, LocalDateTime.of(2015, 10, 25, 13, 0)),
    new Game(Team.Steelers, Team.Chiefs, LocalDateTime.of(2015, 10, 25, 13, 0)),
    new Game(Team.Browns, Team.Rams, LocalDateTime.of(2015, 10, 25, 13, 0)),
    new Game(Team.Texans, Team.Dolphins, LocalDateTime.of(2015, 10, 25, 13, 0)),
    new Game(Team.Jets, Team.Patriots, LocalDateTime.of(2015, 10, 25, 13, 0)),

    //4pm games
    new Game(Team.Raiders, Team.Chargers, LocalDateTime.of(2015, 10, 25, 16, 5)),
    new Game(Team.Cowboys, Team.Giants, LocalDateTime.of(2015, 10, 25, 16, 25)),

    //Sunday Night game
    new Game(Team.Eagles, Team.Panthers, LocalDateTime.of(2015, 10, 25, 20, 30)),

    //Monday Night game
    new Game(Team.Ravens, Team.Cardinals, LocalDateTime.of(2015, 10, 26, 22, 30))
  )

  val week8Games = Seq(
    //Thursday Night game
    new Game(Team.Dolphins, Team.Patriots, LocalDateTime.of(2015, 10, 29, 20, 25)),

    //1pm games
    new Game(Team.Lions, Team.Chiefs, LocalDateTime.of(2015, 11, 1, 9, 30)),
    new Game(Team.Buccaneers, Team.Falcons, LocalDateTime.of(2015, 11, 1, 13, 0)),
    new Game(Team.Cardinals, Team.Browns, LocalDateTime.of(2015, 11, 1, 13, 0)),
    new Game(Team.FourtyNiners, Team.Rams, LocalDateTime.of(2015, 11, 1, 13, 0)),
    new Game(Team.Giants, Team.Saints, LocalDateTime.of(2015, 11, 1, 13, 0)),
    new Game(Team.Vikings, Team.Bears, LocalDateTime.of(2015, 11, 1, 13, 0)),
    new Game(Team.Chargers, Team.Ravens, LocalDateTime.of(2015, 11, 1, 13, 0)),
    new Game(Team.Bengals, Team.Steelers, LocalDateTime.of(2015, 11, 1, 13, 0)),
    new Game(Team.Titans, Team.Texans, LocalDateTime.of(2015, 11, 1, 13, 0)),

    //4pm games
    new Game(Team.Jets, Team.Raiders, LocalDateTime.of(2015, 11, 1, 16, 5)),
    new Game(Team.Seahawks, Team.Cowboys, LocalDateTime.of(2015, 11, 1, 16, 25)),

    //Sunday Night game
    new Game(Team.Packers, Team.Broncos, LocalDateTime.of(2015, 11, 1, 20, 30)),

    //Monday Night game
    new Game(Team.Colts, Team.Panthers, LocalDateTime.of(2015, 11, 2, 22, 30))
  )

  val week9Games = Seq(
    //Thursday Night game
    new Game(Team.Browns, Team.Bengals, LocalDateTime.of(2015, 11, 5, 20, 25)),

    //1pm games
    new Game(Team.Packers, Team.Panthers, LocalDateTime.of(2015, 11, 8, 13, 0)),
    new Game(Team.Redskins, Team.Patriots, LocalDateTime.of(2015, 11, 8, 13, 0)),
    new Game(Team.Titans, Team.Saints, LocalDateTime.of(2015, 11, 8, 13, 0)),
    new Game(Team.Dolphins, Team.Bills, LocalDateTime.of(2015, 11, 8, 13, 0)),
    new Game(Team.Rams, Team.Vikings, LocalDateTime.of(2015, 11, 8, 13, 0)),
    new Game(Team.Jaguars, Team.Jets, LocalDateTime.of(2015, 11, 8, 13, 0)),
    new Game(Team.Raiders, Team.Steelers, LocalDateTime.of(2015, 11, 8, 13, 0)),

    //4pm games
    new Game(Team.Giants, Team.Buccaneers, LocalDateTime.of(2015, 11, 8, 16, 5)),
    new Game(Team.Falcons, Team.FourtyNiners, LocalDateTime.of(2015, 11, 8, 16, 5)),
    new Game(Team.Broncos, Team.Colts, LocalDateTime.of(2015, 11, 8, 16, 25)),

    //Sunday Night game
    new Game(Team.Eagles, Team.Cowboys, LocalDateTime.of(2015, 11, 8, 20, 30)),

    //Monday Night game
    new Game(Team.Bears, Team.Chargers, LocalDateTime.of(2015, 11, 9, 22, 30))
  )

  val week10Games = Seq(
    //Thursday Night game
    new Game(Team.Bills, Team.Jets, LocalDateTime.of(2015, 11, 12, 20, 25)),

    //1pm games
    new Game(Team.Lions, Team.Packers, LocalDateTime.of(2015, 11, 15, 13, 0)),
    new Game(Team.Cowboys, Team.Buccaneers, LocalDateTime.of(2015, 11, 15, 13, 0)),
    new Game(Team.Panthers, Team.Titans, LocalDateTime.of(2015, 11, 15, 13, 0)),
    new Game(Team.Bears, Team.Rams, LocalDateTime.of(2015, 11, 15, 13, 0)),
    new Game(Team.Saints, Team.Redskins, LocalDateTime.of(2015, 11, 15, 13, 0)),
    new Game(Team.Dolphins, Team.Eagles, LocalDateTime.of(2015, 11, 15, 13, 0)),
    new Game(Team.Browns, Team.Steelers, LocalDateTime.of(2015, 11, 15, 13, 0)),
    new Game(Team.Jaguars, Team.Ravens, LocalDateTime.of(2015, 11, 15, 13, 0)),

    //4pm games
    new Game(Team.Vikings, Team.Raiders, LocalDateTime.of(2015, 11, 15, 16, 5)),
    new Game(Team.Patriots, Team.Giants, LocalDateTime.of(2015, 11, 15, 16, 25)),
    new Game(Team.Chiefs, Team.Broncos, LocalDateTime.of(2015, 11, 15, 16, 25)),

    //Sunday Night game
    new Game(Team.Cardinals, Team.Seahawks, LocalDateTime.of(2015, 11, 15, 20, 30)),

    //Monday Night game
    new Game(Team.Texans, Team.Bengals, LocalDateTime.of(2015, 11, 16, 22, 30))
  )

  val week11Games = Seq(
    //Thursday Night game
    new Game(Team.Titans, Team.Jaguars, LocalDateTime.of(2015, 11, 19, 20, 25)),

    //1pm games
    new Game(Team.Raiders, Team.Lions, LocalDateTime.of(2015, 11, 22, 13, 0)),
    new Game(Team.Colts, Team.Falcons, LocalDateTime.of(2015, 11, 22, 13, 0)),
    new Game(Team.Jets, Team.Texans, LocalDateTime.of(2015, 11, 22, 13, 0)),
    new Game(Team.Buccaneers, Team.Eagles, LocalDateTime.of(2015, 11, 22, 13, 0)),
    new Game(Team.Broncos, Team.Bears, LocalDateTime.of(2015, 11, 22, 13, 0)),
    new Game(Team.Packers, Team.Vikings, LocalDateTime.of(2015, 11, 22, 13, 0)),
    new Game(Team.Rams, Team.Ravens, LocalDateTime.of(2015, 11, 22, 13, 0)),
    new Game(Team.Cowboys, Team.Dolphins, LocalDateTime.of(2015, 11, 22, 13, 0)),
    new Game(Team.Redskins, Team.Panthers, LocalDateTime.of(2015, 11, 22, 13, 0)),

    //4pm games
    new Game(Team.Bengals, Team.Cardinals, LocalDateTime.of(2015, 11, 22, 16, 5)),
    new Game(Team.FourtyNiners, Team.Seahawks, LocalDateTime.of(2015, 11, 22, 16, 25)),

    //Sunday Night game
    new Game(Team.Chiefs, Team.Chargers, LocalDateTime.of(2015, 11, 22, 20, 30)),

    //Monday Night game
    new Game(Team.Bills, Team.Patriots, LocalDateTime.of(2015, 11, 23, 22, 30))
  )

  val week12Games = Seq(
    //Thursday Night game
    new Game(Team.Eagles, Team.Lions, LocalDateTime.of(2015, 11, 26, 12, 30)),
    new Game(Team.Panthers, Team.Cowboys, LocalDateTime.of(2015, 11, 26, 16, 30)),
    new Game(Team.Bears, Team.Packers, LocalDateTime.of(2015, 11, 26, 22, 30)),

    //1pm games
    new Game(Team.Saints, Team.Texans, LocalDateTime.of(2015, 11, 29, 13, 0)),
    new Game(Team.Rams, Team.Bengals, LocalDateTime.of(2015, 11, 29, 13, 0)),
    new Game(Team.Vikings, Team.Falcons, LocalDateTime.of(2015, 11, 29, 13, 0)),
    new Game(Team.Giants, Team.Redskins, LocalDateTime.of(2015, 11, 29, 13, 0)),
    new Game(Team.Buccaneers, Team.Colts, LocalDateTime.of(2015, 11, 29, 13, 0)),
    new Game(Team.Bills, Team.Chiefs, LocalDateTime.of(2015, 11, 29, 13, 0)),
    new Game(Team.Raiders, Team.Titans, LocalDateTime.of(2015, 11, 29, 13, 0)),
    new Game(Team.Chargers, Team.Jaguars, LocalDateTime.of(2015, 11, 29, 13, 0)),
    new Game(Team.Dolphins, Team.Jets, LocalDateTime.of(2015, 11, 29, 13, 0)),

    //4pm games
    new Game(Team.Cardinals, Team.FourtyNiners, LocalDateTime.of(2015, 11, 29, 16, 5)),
    new Game(Team.Steelers, Team.Seahawks, LocalDateTime.of(2015, 11, 29, 16, 25)),

    //Sunday Night game
    new Game(Team.Patriots, Team.Broncos, LocalDateTime.of(2015, 11, 29, 20, 30)),

    //Monday Night game
    new Game(Team.Ravens, Team.Browns, LocalDateTime.of(2015, 11, 30, 22, 30))
  )

  val week13Games = Seq(
    //Thursday Night game
    new Game(Team.Packers, Team.Lions, LocalDateTime.of(2015, 12, 3, 22, 25)),

    //1pm games
    new Game(Team.Jets, Team.Giants, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.Cardinals, Team.Rams, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.Falcons, Team.Buccaneers, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.Panthers, Team.Saints, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.Seahawks, Team.Vikings, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.Texans, Team.Bills, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.Ravens, Team.Dolphins, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.Bengals, Team.Browns, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.Jaguars, Team.Titans, LocalDateTime.of(2015, 12, 6, 13, 0)),
    new Game(Team.FourtyNiners, Team.Bears, LocalDateTime.of(2015, 12, 6, 13, 0)),

    //4pm games
    new Game(Team.Broncos, Team.Chargers, LocalDateTime.of(2015, 12, 6, 16, 5)),
    new Game(Team.Chiefs, Team.Raiders, LocalDateTime.of(2015, 12, 6, 16, 5)),
    new Game(Team.Eagles, Team.Patriots, LocalDateTime.of(2015, 12, 6, 16, 25)),

    //Sunday Night game
    new Game(Team.Colts, Team.Steelers, LocalDateTime.of(2015, 12, 6, 20, 30)),

    //Monday Night game
    new Game(Team.Cowboys, Team.Redskins, LocalDateTime.of(2015, 12, 7, 22, 30))
  )

  val week14Games = Seq(
    //Thursday Night game
    new Game(Team.Vikings, Team.Cardinals, LocalDateTime.of(2015, 12, 10, 22, 25)),

    //1pm games
    new Game(Team.Bills, Team.Eagles, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.FourtyNiners, Team.Browns, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Lions, Team.Rams, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Saints, Team.Buccaneers, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Titans, Team.Jets, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Steelers, Team.Bengals, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Patriots, Team.Texans, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Colts, Team.Jaguars, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Chargers, Team.Chiefs, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Redskins, Team.Bears, LocalDateTime.of(2015, 12, 13, 13, 0)),
    new Game(Team.Falcons, Team.Panthers, LocalDateTime.of(2015, 12, 13, 13, 0)),

    //4pm games
    new Game(Team.Raiders, Team.Broncos, LocalDateTime.of(2015, 12, 13, 16, 5)),
    new Game(Team.Cowboys, Team.Packers, LocalDateTime.of(2015, 12, 13, 16, 25)),

    //Sunday Night game
    new Game(Team.Seahawks, Team.Ravens, LocalDateTime.of(2015, 12, 13, 20, 30)),

    //Monday Night game
    new Game(Team.Giants, Team.Dolphins, LocalDateTime.of(2015, 12, 14, 22, 30))
  )

  val week15Games = Seq(
    //Thursday Night game
    new Game(Team.Buccaneers, Team.Rams, LocalDateTime.of(2015, 12, 17, 22, 25)),

    //Saturday game
    new Game(Team.Jets, Team.Cowboys, LocalDateTime.of(2015, 12, 19, 22, 25)),

    //1pm games
    new Game(Team.Bears, Team.Vikings, LocalDateTime.of(2015, 12, 20, 13, 0)),
    new Game(Team.Falcons, Team.Jaguars, LocalDateTime.of(2015, 12, 20, 13, 0)),
    new Game(Team.Texans, Team.Colts, LocalDateTime.of(2015, 12, 20, 13, 0)),
    new Game(Team.Cardinals, Team.Eagles, LocalDateTime.of(2015, 12, 20, 13, 0)),
    new Game(Team.Panthers, Team.Giants, LocalDateTime.of(2015, 12, 20, 13, 0)),
    new Game(Team.Titans, Team.Patriots, LocalDateTime.of(2015, 12, 20, 13, 0)),
    new Game(Team.Bills, Team.Redskins, LocalDateTime.of(2015, 12, 20, 13, 0)),
    new Game(Team.Chiefs, Team.Ravens, LocalDateTime.of(2015, 12, 20, 13, 0)),

    //4pm games
    new Game(Team.Browns, Team.Seahawks, LocalDateTime.of(2015, 12, 20, 16, 5)),
    new Game(Team.Packers, Team.Raiders, LocalDateTime.of(2015, 12, 20, 16, 5)),
    new Game(Team.Broncos, Team.Steelers, LocalDateTime.of(2015, 12, 20, 16, 25)),
    new Game(Team.Dolphins, Team.Chargers, LocalDateTime.of(2015, 12, 20, 16, 25)),

    //Sunday Night game
    new Game(Team.Bengals, Team.FourtyNiners, LocalDateTime.of(2015, 12, 20, 20, 30)),

    //Monday Night game
    new Game(Team.Lions, Team.Saints, LocalDateTime.of(2015, 12, 21, 22, 30))
  )

  val week16Games = Seq(
    //Thursday Night game
    new Game(Team.Chargers, Team.Raiders, LocalDateTime.of(2015, 12, 24, 22, 25)),

    //Saturday game
    new Game(Team.Redskins, Team.Eagles, LocalDateTime.of(2015, 12, 26, 22, 25)),

    //1pm games
    new Game(Team.Patriots, Team.Jets, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.Texans, Team.Titans, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.Browns, Team.Chiefs, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.Colts, Team.Dolphins, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.Jaguars, Team.Saints, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.FourtyNiners, Team.Lions, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.Cowboys, Team.Bills, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.Bears, Team.Buccaneers, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.Panthers, Team.Falcons, LocalDateTime.of(2015, 12, 27, 13, 0)),
    new Game(Team.Giants, Team.Vikings, LocalDateTime.of(2015, 12, 27, 13, 0)),

    //4pm games
    new Game(Team.Rams, Team.Seahawks, LocalDateTime.of(2015, 12, 27, 16, 25)),
    new Game(Team.Packers, Team.Cardinals, LocalDateTime.of(2015, 12, 27, 16, 25)),

    //Sunday Night game
    new Game(Team.Steelers, Team.Ravens, LocalDateTime.of(2015, 12, 27, 20, 30)),

    //Monday Night game
    new Game(Team.Bengals, Team.Broncos, LocalDateTime.of(2015, 12, 28, 22, 30))
  )

  val week17Games = Seq(
    //1pm games
    new Game(Team.Jets, Team.Bills, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Patriots, Team.Dolphins, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Buccaneers, Team.Panthers, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Saints, Team.Falcons, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Ravens, Team.Bengals, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Steelers, Team.Browns, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Jaguars, Team.Texans, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Titans, Team.Colts, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Raiders, Team.Chiefs, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Redskins, Team.Cowboys, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Eagles, Team.Giants, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Lions, Team.Bears, LocalDateTime.of(2016, 1, 3, 13, 0)),
    new Game(Team.Vikings, Team.Packers, LocalDateTime.of(2016, 1, 3, 13, 0)),

    //4pm games
    new Game(Team.Chargers, Team.Broncos, LocalDateTime.of(2016, 1, 3, 16, 25)),
    new Game(Team.Seahawks, Team.Cardinals, LocalDateTime.of(2016, 1, 3, 16, 25)),
    new Game(Team.Rams, Team.FourtyNiners, LocalDateTime.of(2016, 1, 3, 16, 25))
  )

}
