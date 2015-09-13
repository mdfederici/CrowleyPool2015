package administration

import domain._

object DataGenerator {
  def makeUserMetaData(picks: Map[Week, Seq[Pick]]): UserMetaData = {
    val metaData =
      Week.allWeeks.map(week => {
        (week, createMetaData(picks(week)))
      })

    UserMetaData(metaData)
  }

  private def createMetaData(picks: Seq[Pick]): WeeklyMetaData = {
    WeeklyMetaData(picks.toSet)
  }

  /*

  private def getDefaultPicks(week: Week): Set[Pick] = {
    val games =
      week.number match {
        case 1 => Game.week1Games
        case 2 => Game.week2Games
        case 3 => Game.week3Games
        case 4 => Game.week4Games
        case 5 => Game.week5Games
        case 6 => Game.week6Games
        case 7 => Game.week7Games
        case 8 => Game.week8Games
        case 9 => Game.week9Games
        case 10 => Game.week10Games
        case 11 => Game.week11Games
        case 12 => Game.week12Games
        case 13 => Game.week13Games
        case 14 => Game.week14Games
        case 15 => Game.week15Games
        case 16 => Game.week16Games
        case 17 => Game.week17Games
      }

    implicit def ordering = DateCalculator.dateTimeOrdering
    val orderedGames = games.sortBy(x => x.kickoff)

    val random = new Random()
    val confidenceQueue =
      games.size match {
        case 16 => mutable.Queue[Int](8, 9, 7, 10, 6, 11, 5, 12, 4, 13, 3, 14, 2, 15, 1, 16)
        case 15 => mutable.Queue[Int](9, 10, 8, 11, 7, 12, 6, 13, 5, 14, 4, 15, 3, 16, 2)
        case 14 => mutable.Queue[Int](9, 10, 8, 11, 7, 12, 6, 13, 5, 14, 4, 15, 3, 16)
        case 13 => mutable.Queue[Int](10, 11, 9, 12, 8, 13, 7, 14, 6, 15, 5, 16, 4)
        case _ => throw new Exception("Unexpected number of games in a given week.")
      }

    val picks = orderedGames.map(game => {
      val selectedTeam =
        random.nextBoolean() match {
          case true => game.homeTeam
          case false => game.awayTeam
        }

      val conf = Confidence(confidenceQueue.dequeue())
      new Pick(game, selectedTeam, conf)
    })

    picks.toSet
  }*/
}
