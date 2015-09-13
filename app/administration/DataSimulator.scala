package administration

import domain._

import scala.collection.mutable
import scala.util.Random

object DataSimulator {
  private def printPerformance(user: User) = {
    println(s"Performance data for $user:")
    println(s"---Record of ${user.record()}")
    println(s"---Score of ${user.metaData.pointsEarned()}")
    println(s"---PointsRemaining of ${user.metaData.potentialPointsRemaining()}")
  }

  def simulateAFewWeeks(users: Seq[User]): Unit = {

    for(week <- Week.allWeeks.take(5)) {
      for(user <- users) {
        simulatePicks(user.picks(week))
      }

      simulateWinners(Game.getGames(week))

      for(user <- users) {
        printPerformance(user)
      }
    }
  }

  private def simulatePicks(picks: Set[Pick]) = {
    val floor = 17 - picks.size

    val shuffle: Seq[Int] = Random.shuffle(floor to 16).toIndexedSeq
    val confidenceQueue = mutable.Queue[Int](shuffle: _*)
    println(s"making the picks(${picks.size}): " + confidenceQueue)

    picks.foreach(pick => {
      val random = new Random()

      random.nextDouble() match {
        case x if x >= 0.5 => pick.selectedTeam = pick.game.homeTeam
        case _ => pick.selectedTeam = pick.game.awayTeam
      }

      val selectedConfidence = Confidence(confidenceQueue.dequeue())
      pick.confidence = selectedConfidence

      println(s"picking ${pick.selectedTeam.abbreviation} for ${pick.confidence.rating}")
    })
  }

  private def simulateWinners(games: Seq[Game]) = {
    games.foreach(game => {
      val random = new Random()
      val homeScore = random.nextInt(4) * 7 + random.nextInt(3) * 3
      val awayScore = random.nextInt(4) * 7 + random.nextInt(3) * 3

      val winner =
        homeScore match {
          case i if i >= awayScore => game.homeTeam
          case _ => game.awayTeam
        }

      game.winner = winner
      game.homeScore = homeScore
      game.awayScore = awayScore
    })
  }
}
