package logic

import java.time.LocalDateTime

import domain.{Game, Week}

object DateCalculator {
  def dateTimeOrdering: Ordering[LocalDateTime] = Ordering.fromLessThan(_ isBefore _)

  def calculateThursdayLockTime(week: Week): LocalDateTime = {
    implicit def ordering = DateCalculator.dateTimeOrdering
    val thursdayGame = Game.getGames(week).sortBy(x => x.kickoff).head
    val lockTime = thursdayGame.kickoff.minusMinutes(5)
    lockTime
  }
}
