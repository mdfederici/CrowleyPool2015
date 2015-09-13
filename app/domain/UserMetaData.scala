package domain

case class UserMetaData(weeklyMetaData: Seq[(Week, WeeklyMetaData)]) {

  val orderedMetaData = weeklyMetaData.sortBy(x => x._1.number)

  def getMetaData(week: Week): WeeklyMetaData = {
    val metadata = weeklyMetaData.find(x => x._1 == week).getOrElse(throw new Exception(s"Failed to calculate points remaining. Could not find meta data for week $week."))
    metadata._2
  }

  def pointsEarned(week: Option[Week] = None): Int = {
    week match {
      case Some(w) => weeklyMetaData.find(x => x._1 == w).get._2.earnedPoints() //todo: the .get here might fail
      case None => weeklyMetaData.map(_._2.earnedPoints()).sum
    }
  }

  def potentialPointsRemaining(week: Option[Week] = None): Int = {
    week match {
      case None => Week.allWeeks.map(week => potentialPointsRemaining(Some(week))).sum
      case Some(currentWeek) => {
        val thisWeeksPoints = getMetaData(currentWeek).potentialPointsRemaining()
        thisWeeksPoints
      }
    }
  }

  def wins(week: Option[Week] = None): Int = {
    week match {
      case Some(w) => weeklyMetaData.find(x => x._1 == w).get._2.wins() //todo: the .get here might fail
      case None => weeklyMetaData.map(x => x._2.wins()).sum
    }
  }

  def losses(week: Option[Week] = None): Int = {
    week match {
      case Some(w) => weeklyMetaData.find(x => x._1 == w).get._2.losses() //todo: the .get here might fail
      case None => weeklyMetaData.map(x => x._2.losses()).sum
    }
  }

  def winPercentage(week: Option[Week] = None): Double = {
    val myWins = wins(week)
    val total = myWins + losses(week)

    total match {
      case x if x == 0 => 0.0
      case _ => myWins.toDouble / total.toDouble
    }
  }
}
