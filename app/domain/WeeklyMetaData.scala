package domain

case class WeeklyMetaData(picks: Set[Pick]) extends HasRecord {
  def earnedPoints(): Int = {
    val winners =
      picks.filter(p => p.isWinner match {
        case Some(wasWinner) if wasWinner => true
        case _ => false
      })

    val pointTotal = winners.map(x => x.confidence.rating).sum
    pointTotal
  }

  def potentialPointsRemaining(): Int = {
    val unsettledPicks =
      picks.filter(p => p.game.winner match {
        case Some(w) => false
        case None => true
      })

    val pointsRemaining = unsettledPicks.map(x => x.confidence.rating).sum
    pointsRemaining
  }

  override def wins(week: Option[Week] = None) = { //todo: this option week isn't used
    picks.count(x => x.isWinner match {
      case Some(w) => w
      case None => false
    })
  }

  override def losses(week: Option[Week] = None) = { //todo: this option week isn't used
    picks.count(x => x.isWinner match {
      case Some(w) => !w
      case None => false
    })
  }
}
