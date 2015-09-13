package domain

case class User(username: String,
                password: String,
                firstName: String,
                lastName: String,
                hasPaidEntry: Boolean,
                metaData: UserMetaData) extends HasRecord {

  def picks(week: Week): Set[Pick] = {
    val picks = metaData.getMetaData(week).picks
    picks
  }

  def pick(week: Week, game: Game): Pick = {
    val pick = metaData.getMetaData(week).picks.find(x => x.game.shortString == game.shortString).get
    pick
  }

  override def toString = {
    s"$username"
  }

  override def wins(week: Option[Week] = None): Int = metaData.wins(week)
  override def losses(week: Option[Week] = None): Int = metaData.losses(week)
}
