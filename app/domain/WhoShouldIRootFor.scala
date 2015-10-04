package domain

case class WhoShouldIRootFor(week: Week, opponent: User, game: Game, rootFor: Team, diff: Int, reason: Option[String] = None) {
  override def toString: String = {
    s"You should root for ${rootFor.abbreviation} in ${game.shortString} from diff ${diff}."
  }
}