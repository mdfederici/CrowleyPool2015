package domain

class Pick(g: Game,
           s: Team,
           c: Confidence) {

  private var _selectedTeam: Team = s
  private var _confidence: Confidence = c
  val game = g

  def selectedTeam = _selectedTeam

  def confidence = _confidence

  def selectedTeam_=(team: Team): Unit = {

    isLocked match {
      case true => throw new Exception(s"You cannot change your pick for the winner of $game because the pick is locked.")
      case false =>
    }

    team match {
      case t:Team if !game.isTeamParticipating(t) => //noinspection RedundantBlock
      {
        throw new Exception(s"You cannot pick $team as the winner of $game because they are not playing in it.")
      }
      case _ =>
    }

    _selectedTeam = team
  }

  def confidence_=(conf: Confidence): Unit = {
    isLocked match {
      case true => throw new Exception(s"You cannot change the confidence of your pick for $game because the pick is locked.")
      case false =>
    }

    _confidence = conf
  }

  def isWinner: Option[Boolean] = {
    game.winner match {
      case Some(winner) => winner match {
        case w if w == selectedTeam => Some(true)
        case _ => Some(false)
      }
      case None => None
    }
  }

  def isLocked: Boolean = {
    if(game.winner.isDefined)
      return true

    val week = Game.getWeek(game)
//    val lockDateTime = DateCalculator.calculateThursdayLockTime(week)

//    println(s"checking if $game in $week is locked")
//    println(s"go lock time of $lockDateTime which is after ${CrowleyDateTime.now}:${lockDateTime.isAfter(CrowleyDateTime.now)}")

    week.lockDateTime match {
      case k if k.isAfter(CrowleyDateTime.now) => false
      case _ => true
    }
  }
}
