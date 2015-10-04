package domain

case class WhoShouldIRootFor(week: Week, opponent: User, game: Game, rootFor: Team, diff: Int) {
  override def toString: String = {
    s"You should root for ${rootFor.abbreviation} in ${game.shortString} from diff ${diff}."
  }
}

case class WhoShouldIRootForInTheMoney(week: Week, opponents: Seq[User], game: Game, rootFor: Team, diff: Int) {
  override def toString: String = {
    s"You should root for ${rootFor.abbreviation} in ${game.shortString} from diff ${diff}."
  }
}